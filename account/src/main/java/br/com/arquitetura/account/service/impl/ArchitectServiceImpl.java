package br.com.arquitetura.account.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arquitetura.account.converter.ArchitectConverter;
import br.com.arquitetura.account.data.ArchitectCustomersReportData;
import br.com.arquitetura.account.data.ArchitectData;
import br.com.arquitetura.account.data.CustomerReportData;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.Architect;
import br.com.arquitetura.account.exception.ArchitectNotFoundException;
import br.com.arquitetura.account.repository.ArchitectRepository;
import br.com.arquitetura.account.service.ArchitectService;
import br.com.arquitetura.account.service.CustomerService;
import br.com.arquitetura.account.service.GenerateReportService;
import br.com.arquitetura.account.service.UserService;
import net.sf.jasperreports.engine.JRException;

@Service
public class ArchitectServiceImpl implements ArchitectService {
	
	@Autowired
	private ArchitectRepository architectRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private GenerateReportService generateReportService;
	@Value("classpath:reports/architectCustomersReport.jrxml")
	private Resource architectCustomersResource;
	@Value("classpath:reports/customersReport.jrxml")
	private Resource customersResource;
	
	@Override
	public Long save(ArchitectData architectData) {
		architectData.validateCreate();
		
		UserData userSaved = userService.save(architectData.getUser());
		
		Architect architect = ArchitectConverter.convertToArchitect(architectData, userSaved);
		
		Architect architectSaved = architectRepository.save(architect);
		return architectSaved.getUid();
	}

	@Override
	public void update(ArchitectData architectData) {
		architectData.validateUpdate();
		
		Architect architectDataBase = getArchitectById(architectData.getUid());
		
		Architect architect = ArchitectConverter.convertToArchitect(architectDataBase, architectData);
		
		architectRepository.save(architect);
	}

	@Override
	@Transactional(readOnly=true)
	public ArchitectData findByUid(Long uidArchitect) {
		Architect architect = getArchitectById(uidArchitect);
		return ArchitectConverter.convertToArchitectData(architect);
	}

	private Architect getArchitectById(Long uidArchitect) {
		Optional<Architect> architectOptional = architectRepository.findById(uidArchitect);
		if(architectOptional.isPresent()) {
			return architectOptional.get();
		} else {
			throw new ArchitectNotFoundException();
		}
	}

	@Override
	@Transactional(readOnly=true)
	public ArchitectData findByUserUid(Long uidUser) {
		Architect architect = findArchitectByUserUid(uidUser);
		return ArchitectConverter.convertToArchitectData(architect);
	}

	private Architect findArchitectByUserUid(Long uidUser) {
		Architect architect = architectRepository.findByUserUid(uidUser);
		if(architect == null || architect.getUid() == null) {
			throw new ArchitectNotFoundException();
		}
		return architect;
	}

	@Override
	@Transactional(readOnly=true)
	public void generateReportCustomersByArchitect(Long uidUserArchitect) throws IOException, JRException {
		ArchitectCustomersReportData architectCustomersReportData = new ArchitectCustomersReportData();
		
		Architect architect = findArchitectByUserUid(uidUserArchitect);
		architectCustomersReportData.setName(architect.getUser().getName());
		architectCustomersReportData.setEmail(architect.getUser().getEmail());
		architectCustomersReportData.setWhatsapp(architect.getWhatsapp());

		List<CustomerReportData> customersReportData = customerService.getCustomersReportDataByUidUserArchitect(uidUserArchitect);
		architectCustomersReportData.setCustomersReportData(customersReportData);
		
		generateReportService.getRelatorio(architectCustomersResource.getInputStream(), customersResource.getInputStream(), architectCustomersReportData);
	}
}
