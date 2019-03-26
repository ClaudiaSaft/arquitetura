package br.com.arquitetura.account.service;

import br.com.arquitetura.account.data.ArchitectData;

public interface ArchitectService {

	ArchitectData findByUid(Long uidArchitect);

	Long save(ArchitectData architectData);

	void update(ArchitectData architectData);

	ArchitectData findByUserUid(Long uidUser);

}
