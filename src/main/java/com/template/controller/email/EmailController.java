package com.template.controller.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin()
@RequestMapping(value = "/email")
@Api(value="email")
@Profile("email")
public class EmailController {

	@Autowired 
	private JavaMailSender mailSender;

    @RequestMapping(path = "/sendMail/{mensagem}", method = RequestMethod.GET)
    public String sendMail(@PathVariable("mensagem") String mensagem) {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(mensagem);
        message.setTo("arao.alves7@gmail.com");
        message.setFrom("arao.alves7@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
    
    @RequestMapping(path = "/sendMailHtml", method = RequestMethod.GET)
    public String sendMailHtml() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "arao.alves7@gmail.com" );
            helper.setSubject( "Teste Envio de e-mail" );
            helper.setText("<p>Teste de envio de e-mail via Spring Boot</p>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
	
}
