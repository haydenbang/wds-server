package com.wds.server.wdsserver.commons.exceptions;

import com.wds.server.wdsserver.commons.enums.EnumMessage;

public class BadRequestException extends RuntimeException {
    private EnumMessage enumMessage;

    public BadRequestException() {
        super(EnumMessage.HTTP_BAD_REQUEST.getMessage());
        this.enumMessage = EnumMessage.HTTP_BAD_REQUEST;
    }

    public BadRequestException(EnumMessage enumMessage) {
        super(enumMessage.getMessage());
        this.enumMessage = enumMessage;
    }
    public EnumMessage getEnumMessage() {
        return enumMessage;
    }
}
