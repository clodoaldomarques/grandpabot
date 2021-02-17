package br.com.cgms.grandpabot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cgms.grandpabot.service.SigajusBot;

@Configuration
public class BotsConfig {
	
	@Bean
	public SigajusBot sigajusBot() {
		return new SigajusBot();
	}

}
