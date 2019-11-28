package com.wds.server.wdsserver.controller;

import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Api(value = "User", tags = "User")
public class UserController {

    private final UserService userService;
    private final ResponseType responseType;

    @GetMapping("")
    @ApiOperation(value = "사용자 리스트 조회", nickname = "사용자 리스트 조회", notes = "조건에 따라 사용자 정보를 가져온다.")
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "filter", defaultValue = "All") String filter, @ApiParam(name = "keyword", value = "검색키워드(이름, 휴대폰 번호)") @RequestParam(value = "keyword", required = false) String query) {
        return responseType.send(userService.getAllUsersOrderByCreatedDate(filter, query));
    }

//    @GetMapping("/lookup")
//    @ApiOperation(value="사용자 조회", nickname = "사용자 조회", notes = "조건에 맞는 사용자(들)를 가져온다.")
//    public ResponseEntity<?> lookUpUsers(@RequestParam(value = "filter", defaultValue = "All") String filter, @RequestParam("query") String query){
//        return responseType.send(userService.lookUpUsers(filter, query));
//    }

    @GetMapping("/{idx}")
    @ApiOperation(value = "사용자 조회", nickname = "사용자 조회", notes = "사용자 정보를 가져온다.")
    public ResponseEntity<?> getUser(@PathVariable("idx") Long idx) throws Exception {
        return responseType.send(userService.getUser(idx));
    }

    @PostMapping("")
    @ApiOperation(value = "사용자 저장", nickname = "사용자 저장", notes = "사용자 정보를 저장한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "주소", required = true, dataType = "String", defaultValue = "서울시 용산구"),
            @ApiImplicitParam(name = "authority", value = "권한", required = true, dataType = "String", defaultValue = "NORMAL"),
            @ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "String", defaultValue = "wds2019"),
            @ApiImplicitParam(name = "name", value = "이름", required = true, dataType = "String", defaultValue = "홍길동"),
            @ApiImplicitParam(name = "nick_name", value = "닉네임", required = true, dataType = "String", defaultValue = "길"),
            @ApiImplicitParam(name = "pass", value = "비밀번호", required = true, dataType = "String", defaultValue = "qwert12345"),
            @ApiImplicitParam(name = "photo", value = "사진", dataType = "mediumblob"),
            @ApiImplicitParam(name = "tel", value = "휴대폰 번호", required = true, dataType = "String", defaultValue = "01012344321"),
    })
    public ResponseEntity<?> addUser(@Valid @ModelAttribute User user) {
        return responseType.send(userService.addUser(user));
    }

    @PutMapping("/{idx}")
    @ApiOperation(value = "사용자 정보 수정", nickname = "사용자 정보 수정", notes = "사용자 정보를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "주소", required = true, dataType = "String", defaultValue = "서울시 용산구"),
            @ApiImplicitParam(name = "authority", value = "권한", required = true, dataType = "enum", defaultValue = "NORMAL"),
            @ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "String", defaultValue = "wds2019"),
            @ApiImplicitParam(name = "name", value = "이름", required = true, dataType = "String", defaultValue = "홍길동"),
            @ApiImplicitParam(name = "nick_name", value = "닉네임", required = true, dataType = "String", defaultValue = "길"),
            @ApiImplicitParam(name = "pass", value = "비밀번호", required = true, dataType = "String", defaultValue = "qwert12345"),
            @ApiImplicitParam(name = "photo", value = "사진", dataType = "mediumblob"),
            @ApiImplicitParam(name = "tel", value = "휴대폰 번호", required = true, dataType = "String", defaultValue = "01012344321"),
    })
    public ResponseEntity<?> updateUser(@ApiParam(name = "idx", value = "사용자 번호", required = true) @PathVariable("idx") Long idx,
                                        @Valid @ModelAttribute User userDetail) {
        return responseType.send(userService.updateUser(userDetail));
    }

    @DeleteMapping("/{idx}")
    @ApiOperation(value = "사용자 삭제", nickname = "사용자 삭제", notes = "사용자 정보를 삭제한다.")
    public ResponseEntity<?> deleteUser(@ApiParam(name = "idx", value = "사용자 번호", required = true) @PathVariable("idx") Long idx) throws Exception {
        userService.deleteUser(idx);
        return responseType.send();
    }
}
