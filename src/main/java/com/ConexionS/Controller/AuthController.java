package com.ConexionS.Controller;

import com.ConexionS.Entities.Users;
import com.ConexionS.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "login")
    public String login(){
        return "Login from public endpoint";
    }

    @PostMapping(value = "register")
    public String register(){
        return "Register form public endpoint";
    }
}
