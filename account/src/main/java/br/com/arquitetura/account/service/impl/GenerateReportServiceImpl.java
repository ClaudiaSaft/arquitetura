package br.com.arquitetura.account.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.arquitetura.account.service.GenerateReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Service
public class GenerateReportServiceImpl implements GenerateReportService {

	@Override
	public byte[] getRelatorio(InputStream inputStreamReport, InputStream inputStreamSubReport, Object object) throws JRException {
		JasperReport reportCompiled = JasperCompileManager.compileReport(inputStreamReport);
		JasperReport subreportCompiled = JasperCompileManager.compileReport(inputStreamSubReport);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("SUBREPORT_PARAMETER", subreportCompiled);

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportCompiled, parameters, new JRBeanCollectionDataSource(Arrays.asList(object)));
		
		exportAndSaveFile(jasperPrint);
		return exportInByteArray(jasperPrint);
	}

	private byte[] exportInByteArray(JasperPrint jasperPrint) throws JRException {
		ByteArrayOutputStream baous = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baous);
		return baous.toByteArray();
	}

	private void exportAndSaveFile(JasperPrint jasperPrint) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("/Users/claudiasaft/trabalho/workspaceestudos/arquitetura/account/src/main/resources/reports/architectCustomersReport.pdf"));
		
		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
		exportConfig.setEncrypted(true);

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		exporter.exportReport();
	}

}
