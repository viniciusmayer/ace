package com.eleonorvinicius.ace.exception;

public class ACEException extends Exception {

	private static final long serialVersionUID = 5600263169784890830L;

	private int messageKey;

	public ACEException(int messageKey, String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		this.messageKey = messageKey;
	}

	public ACEException(int messageKey, String detailMessage) {
		super(detailMessage);
		this.messageKey = messageKey;
	}

	public ACEException(int messageKey, Throwable throwable) {
		super(throwable);
		this.messageKey = messageKey;
	}

	public int getMessageKey() {
		return messageKey;
	}

}