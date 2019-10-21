package com.wds.server.wdsserver.controller;

import com.wds.server.wdsserver.domain.User;
import com.wds.server.wdsserver.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    @ApiOperation(value = "전체 사용자 조회", nickname = "전체 사용자 조회", notes = "전체 사용자 정보를 가져온다.")
    @ApiImplicitParams({@ApiImplicitParam(name="name", value = "이름", required = false, dataType = "string", paramType = "path")})
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    @ApiOperation(value = "사용자 저장", nickname = "사용자 저장", notes = "사용자 정보를 저장한다.")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{_id}")
    @ApiOperation(value = "사용자 정보 수정", nickname = "사용자 정보 수정", notes = "사용자 정보를 수정한다.")
    public ResponseEntity<User> updateUser(@PathVariable("_id") Long userId, @Valid @RequestBody User userDetail) {
        User user = userRepository.findById(userId).get();

        user.setName(userDetail.getName());

        final User userUpdater = userRepository.save(user);

        return ResponseEntity.ok(userUpdater);
    }

    @DeleteMapping("/users/{_id}")
    @ApiOperation(value = "사용자 삭제", nickname = "사용자 삭제", notes = "사용자 정보를 삭제한다.")
    public Map<String, Boolean> deleteUser(@PathVariable("_id") Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
