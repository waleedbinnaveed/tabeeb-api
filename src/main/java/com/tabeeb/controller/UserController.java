package com.tabeeb.controller;

import com.tabeeb.entity.User;
import com.tabeeb.service.UserService;
import com.tabeeb.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid User user){
        userService.createUser(user.getUsername(),user.getName(),user.getGender(),user.getAge(),user.getPassword(),
                               user.getMobileNo(),user.getUserType());

        return ResponseEntity.ok(Constant.SUCCESS_USER_CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<User> fetchUser(@PathVariable("uuid") String uuid)
    {
        return ResponseEntity.ok(userService.fetchUser(uuid));
    }

    @GetMapping("/user/{username}/username")
    public ResponseEntity<User> fetchUserByUserName(@PathVariable("username") String username)
    {
        return ResponseEntity.ok(userService.findByUserName(username).get());
    }

}
