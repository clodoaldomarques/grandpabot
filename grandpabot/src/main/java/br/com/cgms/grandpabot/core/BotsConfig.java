package br.com.cgms.grandpabot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cgms.grandpabot.service.GrandpaBot;

@Configuration
public class BotsConfig {
	
	@Bean
	public GrandpaBot grandpaBot() {
		return new GrandpaBot();
	}

}
