package br.com.arquitetura.account.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import br.com.arquitetura.account.constant.AccountConstants;
import br.com.arquitetura.account.data.Email;
import br.com.arquitetura.account.data.EmailAnexo;
import br.com.arquitetura.account.service.EmailService;
import br.com.arquitetura.account.service.ParameterService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private Environment environment;
	
	@Override
	public void sendEmail(Email email) {
		System.out.println(environment.getActiveProfiles()[0]);
		
		this.javaMailSender.send(new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper help = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				help.setTo(email.getDestinatario());
				help.setFrom(getEmailRemetente(email), getNomeRementente(email));
				help.setSubject(email.getAssunto());
				help.setText(email.getCorpo(), email.isCorpoHtml());
				
				if(!email.getAnexos().isEmpty()){
					for (EmailAnexo emailAnexo : email.getAnexos()) {
						help.addAttachment(emailAnexo.getNomeAnexo(), emailAnexo.getAnexoResource());
					}
				}
			}

			private String getNomeRementente(Email email) {
				return getParameterFrom(AccountConstants.REMETENTE_NAME);
			}

			private String getEmailRemetente(Email email) {
				return getParameterFrom(AccountConstants.REMETENTE_EMAIL);
			}
			
			private String getParameterFrom(String parameterConstant){
				return parameterService.getString(parameterConstant);
			}
		});
	}

}
