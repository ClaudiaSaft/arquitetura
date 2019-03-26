package br.com.arquitetura.account.converter;

import br.com.arquitetura.account.data.ArchitectData;
import br.com.arquitetura.account.data.UserData;
import br.com.arquitetura.account.entity.Architect;
import br.com.arquitetura.account.entity.User;

public class ArchitectConverter {

	public static Architect convertToArchitect(ArchitectData architectData, UserData userData) {
		Architect architect = new Architect();
		architect.setWhatsapp(architectData.getWhatsapp());
		architect.setComercialPhone(architectData.getComercialPhone());
		
		User user = UserConverter.convertToUser(userData);
		architect.setUser(user);
		return architect;
	}

	public static ArchitectData convertToArchitectData(Architect architect) {
		UserData userData = UserConverter.convertToUserData(architect.getUser());
		
		return new ArchitectData(architect.getUid(), userData, architect.getWhatsapp(), architect.getComercialPhone());
	}

	public static Architect convertToArchitect(Architect architect, ArchitectData architectData) {
		User user = UserConverter.convertToUser(architect.getUser(), architectData.getUser());
		architect.setUser(user);
		
		architect.setComercialPhone(architectData.getComercialPhone());
		architect.setWhatsapp(architectData.getWhatsapp());
		return architect;
	}

}
