package com.wds.server.wdsserver.commons.exceptions;

import com.wds.server.wdsserver.commons.enums.EnumMessage;

public class UnAuthorizedException extends RuntimeException {
	private EnumMessage enumMessage;

	public UnAuthorizedException() {
		super(EnumMessage.HTTP_UNAUTHORIZED.getMessage());
		this.enumMessage = EnumMessage.HTTP_UNAUTHORIZED;
	}

	public UnAuthorizedException(EnumMessage enumMessage) {
		super(enumMessage.getMessage());
		this.enumMessage = enumMessage;
	}

	public EnumMessage getEnumMessage() {
		return enumMessage;
	}
}
