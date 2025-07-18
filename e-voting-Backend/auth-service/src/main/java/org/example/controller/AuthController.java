package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.UserModel;
import org.example.repo.UserRepo;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserModel userModel) {
       return ResponseEntity.status(HttpStatus.OK).body(userService.login(userModel));
    }
}
