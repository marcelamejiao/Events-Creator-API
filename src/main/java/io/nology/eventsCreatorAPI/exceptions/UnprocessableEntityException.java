package io.nology.eventsCreatorAPI.exceptions;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public UnprocessableEntityException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
