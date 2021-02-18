package br.com.cgms.grandpabot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import br.com.cgms.grandpabot.domain.model.Contato;
import br.com.cgms.grandpabot.domain.repository.ContatoRepository;

public class GrandpaBot extends TelegramLongPollingBot {
	
	@Autowired ContatoRepository contatoRepository;
	
	@PostConstruct
	public void autoRegister() {
		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
			telegramBotsApi.registerBot(this);
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		String cmd = message.getText();
		SendMessage sendMessage = new SendMessage();

		if (cmd == null) {
			if (message.getContact() != null) {
				Contact contact = message.getContact();
				sendMessage.setChatId(contact.getUserID().toString());
				try {
					addContato(contact);
					sendMessage.setText("Inscrição realizada com sucesso.");
				} catch (Exception e) {
					sendMessage.setText("Ocorreu um erro ao efetivar seu registro.");
					e.printStackTrace();
				}
			}
			
			try {
				Message m = execute(sendMessage);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			if (cmd.equals("/start")) {
				sendMessage.setText("Bem vindo a API de envio de mensagens GrandPaBot, clique no botão ENVIAR NÚMERO DO TELEFONE para que possamos identificá-lo.");
				sendMessage.setReplyMarkup(getKeyboardMarkupToGetContact());
				sendMessage.setChatId(update.getMessage().getChatId());
			} else {
				sendMessage.setChatId(update.getMessage().getChatId());
				sendMessage.setText("Comando não identificado.");
			}
			
			try {
				Message m = execute(sendMessage);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(List<Contato> lista, String message) throws TelegramApiException{
		for (Contato contact : lista) {			
			sendMessage(contact, message);
		}		
	}
	
	private void sendMessage(Contato contato, String message) throws TelegramApiException{
		if (contato != null) {
				SendMessage sendMessage = new SendMessage();
				sendMessage.setChatId(contato.getId().toString());
				sendMessage.setText(message);
				execute(sendMessage);
		} else {
			String msg = "Informe um contato válido";
			throw new TelegramApiException(msg);
		}
	}
	
	public Contato findContact(String phone) {
		return contatoRepository.findByTelefone(phone);
	}
	
	private void addContato(Contact contact) throws Exception{
		
		Contato entity = new Contato();
		entity.setId(contact.getUserID());
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(contact.getPhoneNumber());
	 	if (matcher.find()) {	 		
	 		entity.setTelefone(matcher.group());
	 	} else {
	 		throw new Exception("Numero de telefone inválido");
	 	}
		entity.setPrimeiroNome(contact.getFirstName());
		entity.setUltimoNome(contact.getLastName());
		entity.setBotDoCadastro(this.getBotUsername());
		
		contatoRepository.save(entity);
	}
	
	private static ReplyKeyboard getKeyboardMarkupToGetContact() {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		keyboardMarkup.setResizeKeyboard(true);
		keyboardMarkup.setOneTimeKeyboard(true);
		keyboardMarkup.setSelective(true);
		List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
		KeyboardRow row = new KeyboardRow();
		KeyboardButton button = new KeyboardButton();
		button.setText("Enviar Número no Telefone");
		button.setRequestContact(true);
		row.add(button);
		keyboard.add(row);
		keyboardMarkup.setKeyboard(keyboard);
		return keyboardMarkup;
	}

	@Override
	public String getBotUsername() {
		return "cgomesm_bot";
	}

	@Override
	public String getBotToken() {
		return "1608132036:AAH_BZgItSBKZBvKLoUTCHViLvunsl0WbAA";
	}
}
