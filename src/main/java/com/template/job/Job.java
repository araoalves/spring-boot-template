package com.template.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class Job {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Scheduled(fixedRate=1*60*1000) // rodar de 1 em 1 minuto
	public void job1() {
		this.logger.info("Rodando Job1 : "+ dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedRate=2*60*1000, initialDelay=2*60*1000) // rodar de 2 em 2 minutos após 2 minutos
	public void jog2() {
		this.logger.info("Rodando Job2 : "+ dateFormat.format(new Date()));
	}
	
}
