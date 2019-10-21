package com.wds.server.wdsserver.controller;

import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(value = "User", tags = "User")
public class UserController {

    private final UserService userService;
    private final ResponseType responseType;

    public UserController(UserService userService, ResponseType responseType) {
        this.userService = userService;
        this.responseType = responseType;
    }

    @GetMapping("/users")
    @ApiOperation(value = "전체 사용자 조회", nickname = "전체 사용자 조회", notes = "전체 사용자 정보를 가져온다.")
    @ApiImplicitParams({@ApiImplicitParam(name="name", value = "이름", required = false, dataType = "string", paramType = "path")})
    public ResponseEntity<?> getAllUsers() {
        return responseType.send(userService.getAllUsers());
    }

    @PostMapping("/users")
    @ApiOperation(value = "사용자 저장", nickname = "사용자 저장", notes = "사용자 정보를 저장한다.")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        return responseType.send(userService.addUser(user));
    }

    @PutMapping("/users/{_id}")
    @ApiOperation(value = "사용자 정보 수정", nickname = "사용자 정보 수정", notes = "사용자 정보를 수정한다.")
    public ResponseEntity<?> updateUser(@PathVariable("_id") Long userId, @Valid @RequestBody User userDetail) {
        userDetail.setId(userId);
        return responseType.send(userService.updateUser(userDetail));
    }

    @DeleteMapping("/users/{_id}")
    @ApiOperation(value = "사용자 삭제", nickname = "사용자 삭제", notes = "사용자 정보를 삭제한다.")
    public ResponseEntity<?> deleteUser(@PathVariable("_id") Long userId) throws Exception {
        userService.deleteUser(userId);
        return responseType.send();
    }
}
