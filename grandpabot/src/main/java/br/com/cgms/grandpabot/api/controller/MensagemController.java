package br.com.cgms.grandpabot.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.cgms.grandpabot.api.model.Mensagem;
import br.com.cgms.grandpabot.domain.exception.NotFoundBusinessException;
import br.com.cgms.grandpabot.domain.model.Contato;
import br.com.cgms.grandpabot.domain.repository.ContatoRepository;
import br.com.cgms.grandpabot.service.TelegramBot;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
	
	@Autowired TelegramBot telegramBot;
	@Autowired ContatoRepository contatoRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void enviar(@Valid @RequestBody Mensagem mensagem) {
		try {
			List<Contato> contatos = contatoRepository.findByTelefoneContains(mensagem.getNumero());
			telegramBot.sendMessage(contatos, mensagem.getMensagem());
		} catch (TelegramApiException e) {
			throw new NotFoundBusinessException("Numero não encontrado");
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@RequestMapping("/todos")
	public void enviarTodos(@Valid @RequestBody Mensagem mensagem) {
		try {
			List<Contato> contatos = contatoRepository.findAll();
			telegramBot.sendMessage(contatos, mensagem.getMensagem());
		} catch (TelegramApiException e) {
			throw new NotFoundBusinessException("Numero não encontrado");
		}
	}

}