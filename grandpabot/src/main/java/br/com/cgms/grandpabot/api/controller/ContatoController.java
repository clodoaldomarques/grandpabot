package br.com.cgms.grandpabot.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cgms.grandpabot.domain.model.Contato;
import br.com.cgms.grandpabot.domain.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired ContatoRepository contatoRepository;
	
	@GetMapping
	public List<Contato> lista(){
		return contatoRepository.findAll();
	}	
	
	@GetMapping("/{telefone}")
	public ResponseEntity<Contato> buscar(@PathVariable String telefone) {
		Optional<Contato> contato = Optional.ofNullable(contatoRepository.findByTelefone(telefone));
		if (contato.isPresent()) {
			return ResponseEntity.ok(contato.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!contatoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		contatoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
