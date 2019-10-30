package com.wds.server.wdsserver.controller;

import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.commons.enums.EnumMessage;
import com.wds.server.wdsserver.commons.exceptions.BadRequestException;
import com.wds.server.wdsserver.commons.exceptions.UnAuthorizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/error")
@Api(value = "Error", tags = "Error")
public class ErrorController {

    @GetMapping("/auth")
    @ApiOperation(value = "401 Error Test")
    public ResponseEntity<?> test401() throws Exception {
        throw new UnAuthorizedException();
    }

    @GetMapping("/auth/message")
    @ApiOperation(value = "401 Error Test + Message")
    public ResponseEntity<?> test401Message() throws Exception {
        throw new UnAuthorizedException(EnumMessage.ERROR_MESSAGE_TEST);
    }

    @GetMapping("/request")
    @ApiOperation(value = "400 Error Test")
    public ResponseEntity<?> test400() {
        throw new BadRequestException();
    }

    @GetMapping("/request/message")
    @ApiOperation(value = "400 Error Test + Message")
    public ResponseEntity<?> test400Message() {
        throw new BadRequestException(EnumMessage.ERROR_MESSAGE_TEST);
    }

    @GetMapping("/server")
    @ApiOperation(value = "500 Error Test")
    public ResponseEntity<?> test500() throws Exception {
        throw new Exception();
    }
}
