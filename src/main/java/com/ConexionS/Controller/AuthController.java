package com.ConexionS.Controller;

import com.ConexionS.Entities.Role;
import com.ConexionS.Entities.Users;
import com.ConexionS.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        boolean isValidUser = usersService.validateUser(users.getEmail(), users.getPassword());

        if (isValidUser) {
            Map<String, Object> roleInfo = usersService.getRoleAndUserIdByEmail(users.getEmail());

            if (roleInfo != null) {
                Integer roleId = (Integer) roleInfo.get("roleId");
                Integer userId = (Integer) roleInfo.get("userId");

                return ResponseEntity.ok(Map.of(
                        "message", "Login successful",
                        "roleId", roleId,
                        "userId", userId
                ));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role not found for user");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}