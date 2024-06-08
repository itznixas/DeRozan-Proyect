package com.ConexionS.Controller;

import com.ConexionS.Entities.Users;
import com.ConexionS.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users users) {
        boolean isValidUser = usersService.validateUser(users.getEmail(), users.getPassword());

        if(isValidUser) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
