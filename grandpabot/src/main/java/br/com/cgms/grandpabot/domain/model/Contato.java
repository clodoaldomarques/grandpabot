package br.com.cgms.grandpabot.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contato")
public class Contato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5580918081022296444L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
	
    @Column(name = "telefone", nullable = false)
	private String telefone;

    @Column(name = "primeiroNome", nullable = false)
	private String primeiroNome;

    @Column(name = "ultimoNome", nullable = true)
	private String ultimoNome;
    
    @Column(name = "botDoCadastro", nullable = false)
	private String botDoCadastro;
    
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getBotDoCadastro() {
		return botDoCadastro;
	}

	public void setBotDoCadastro(String botDoCadastro) {
		this.botDoCadastro = botDoCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((botDoCadastro == null) ? 0 : botDoCadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((primeiroNome == null) ? 0 : primeiroNome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((ultimoNome == null) ? 0 : ultimoNome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (botDoCadastro == null) {
			if (other.botDoCadastro != null)
				return false;
		} else if (!botDoCadastro.equals(other.botDoCadastro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (primeiroNome == null) {
			if (other.primeiroNome != null)
				return false;
		} else if (!primeiroNome.equals(other.primeiroNome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (ultimoNome == null) {
			if (other.ultimoNome != null)
				return false;
		} else if (!ultimoNome.equals(other.ultimoNome))
			return false;
		return true;
	}
	
	
	
}
