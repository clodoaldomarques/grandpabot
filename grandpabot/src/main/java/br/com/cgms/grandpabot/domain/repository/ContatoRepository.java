package br.com.cgms.grandpabot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cgms.grandpabot.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
	public Contato findByTelefone(String telefone);
	
	public Contato findByTelefoneAndBotDoCadastro(String telefone, String botDoCadastro);

	public List<Contato> findByTelefoneContains(String telefone);
	
	public List<Contato> findByBotDoCadastro(String botDoCadastro);

}
