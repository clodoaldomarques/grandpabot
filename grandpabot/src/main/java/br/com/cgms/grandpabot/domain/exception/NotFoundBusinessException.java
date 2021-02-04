package br.com.cgms.grandpabot.domain.exception;

public class NotFoundBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundBusinessException(String message) {
		super(message);
	}

}
