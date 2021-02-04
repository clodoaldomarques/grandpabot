package br.com.cgms.grandpabot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cgms.grandpabot.service.TelegramBot;

@Configuration
public class TelegramBotConfig {
	
	@Bean
	public TelegramBot telegramBot() {
		return new TelegramBot();
	}

}
