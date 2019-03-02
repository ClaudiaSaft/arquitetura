package br.com.arquitetura.converter;

import javax.validation.Valid;

import br.com.arquitetura.data.ArchitectData;
import br.com.arquitetura.data.UserData;
import br.com.arquitetura.entity.Architect;
import br.com.arquitetura.entity.User;

public class ArchitectConverter {

	public static Architect convertToArchitect(ArchitectData architectData) {
		Architect architect = new Architect();
		architect.setWhatsapp(architectData.getWhatsapp());
		architect.setComercialPhone(architectData.getComercialPhone());
		return architect;
	}

	public static ArchitectData convertToArchitectData(Architect architect) {
		UserData userData = UserConverter.convertToUserData(architect.getUser());
		
		return new ArchitectData(architect.getUid(), userData, architect.getWhatsapp(), architect.getComercialPhone());
	}

	public static Architect convertToArchitect(Architect architect, @Valid ArchitectData architectData) {
		User user = UserConverter.convertToUser(architect.getUser(), architectData.getUser());
		architect.setUser(user);
		
		architect.setComercialPhone(architectData.getComercialPhone());
		architect.setWhatsapp(architectData.getWhatsapp());
		return architect;
	}

}
