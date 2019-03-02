package br.com.arquitetura.service;

import br.com.arquitetura.data.ArchitectData;

public interface ArchitectService {

	ArchitectData findByUid(Long uidArchitect);

	Long save(ArchitectData architectData);

	void update(ArchitectData architectData);

}
