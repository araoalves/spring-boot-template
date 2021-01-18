package com.template.controller.email;

import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
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
    public String sendMailHtml() throws Exception {
    	
    	Template template;
		String mensagem;
    	
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            
            VelocityEngine ve = new VelocityEngine();

			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");


			ve.init();
			template = ve.getTemplate("velocity/template.vm", "UTF-8");
			VelocityContext context = new VelocityContext();
			
			context.put("nome", "Arão Farias");

			StringWriter writer = new StringWriter();
			template.merge(context, writer);

			mensagem = writer.toString();
			
			ClassPathResource pdf = new ClassPathResource("email/Teste.pdf");

            MimeMessageHelper helper = new MimeMessageHelper(mail, true, CharEncoding.UTF_8);
            helper.setTo("arao.alves7@gmail.com");
            helper.setFrom("arao.alves7@gmail.com");
            helper.setSubject("Teste Envio de e-mail");
            helper.setText(mensagem, true);
            helper.addAttachment("attachment.pdf", pdf);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
        	throw new Exception(e.getMessage());
        }
    }
	
}
