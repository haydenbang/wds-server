package com.wds.server.wdsserver.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class ResponseType {
    private static String status = "status";
    private static String response = "result";

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
