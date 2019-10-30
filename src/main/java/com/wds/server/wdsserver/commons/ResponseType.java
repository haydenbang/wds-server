package com.wds.server.wdsserver.commons;

import com.wds.server.wdsserver.commons.enums.EnumMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class ResponseType {
    private static String status = "status";
    private static String message = "message";
    private static String response = "result";

    public ResponseEntity<?> send(HttpStatus statusCode, EnumMessage enumMessage, Object body) {
        if (enumMessage == null) {
            enumMessage = EnumMessage.OK;
        }

        HashMap<String, Object> results = new HashMap<>();
        results.put(status, statusCode);
        results.put(message, enumMessage);
        results.put(response, body);

        return ResponseEntity.status(statusCode.value()).body(results);
    }

    public ResponseEntity<?> send(Object body) {
        HashMap<String, Object> results = new HashMap<>();
        results.put(status, HttpStatus.OK.value());
        results.put(response, body);

        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    public ResponseEntity<?> send() {
        return this.send(null);
    }
}
