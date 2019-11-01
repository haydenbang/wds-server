package com.wds.server.wdsserver.commons.enums;

import lombok.Getter;

public enum EnumMessage {
    HTTP_SERVER_ERROR           ("서버에러가 발생하였습니다."),
    HTTP_NOT_FOUND              ("존재하지 않는 API 입니다."),
    HTTP_UNAUTHORIZED           ("권한이 없습니다."),
    HTTP_BAD_REQUEST            ("요청이 잘못되었습니다."),
    ERROR_MESSAGE_TEST          ("테스트 에러 진행중입니다."),

    OK("");

    @Getter
    private String message;

    EnumMessage(String message) {
        this.message = message;
    }
}
