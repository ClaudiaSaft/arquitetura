package br.com.arquitetura.account.service;

import java.io.IOException;

import br.com.arquitetura.account.data.ArchitectData;
import net.sf.jasperreports.engine.JRException;

public interface ArchitectService {

	ArchitectData findByUid(Long uidArchitect);

	Long save(ArchitectData architectData);

	void update(ArchitectData architectData);

	ArchitectData findByUserUid(Long uidUser);

	void generateReportCustomersByArchitect(Long uidUserArchitect) throws IOException, JRException;
}
