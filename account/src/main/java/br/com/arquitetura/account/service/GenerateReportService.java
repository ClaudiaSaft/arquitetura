package br.com.arquitetura.account.service;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;

public interface GenerateReportService {

	byte[] getRelatorio(InputStream inputStreamReport, InputStream inputStreamSubReport, Object object) throws JRException; // throws JRException, IOException;
}
