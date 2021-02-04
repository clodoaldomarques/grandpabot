package br.com.cgms.grandpabot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cgms.grandpabot.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
	public Contato findByTelefone(String telefone);

	public List<Contato> findByTelefoneContains(String telefone);

}
