package com.ConexionS.Controller;

import com.ConexionS.Entities.Role;
import com.ConexionS.Entities.Users;
import com.ConexionS.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register-user")
    public ResponseEntity<Users> registerUser(@RequestBody Users users) {
        // Set registration date
        LocalDateTime localDateTime = LocalDateTime.now();
        Date registrationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        users.setRegistrationDate(registrationDate);

        users.setStatus("active");

        Role defaultRole = new Role();
        defaultRole.setId_role(1);
        users.setRole(defaultRole);

        // Save the user
        Users newUser = usersService.createUser(users);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<Users> registerAdmin(@RequestBody Users users) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date registrationDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        users.setRegistrationDate(registrationDate);
        users.setStatus("active");

        // Verificar si el rol está presente
        if (users.getRole() == null) {
            // Manejar el caso donde el rol no está asignado
            throw new IllegalArgumentException("El rol del usuario no puede ser nulo.");
        }

        // Save the user
        Users newUser = usersService.createUser(users);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @GetMapping("/get-all-users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Optional<Users> user = usersService.getUsersById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/update-user/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users usersDetails) {
        Optional<Users> existingUserOptional = usersService.getUsersById(id);

        return existingUserOptional.map(existingUser -> {

            existingUser.setName(usersDetails.getName() != null ? usersDetails.getName() : existingUser.getName());
            existingUser.setLastName(usersDetails.getLastName() != null ? usersDetails.getLastName() : existingUser.getLastName());
            existingUser.setBirthDate(usersDetails.getBirthDate() != null ? usersDetails.getBirthDate() : existingUser.getBirthDate());
            existingUser.setEmail(usersDetails.getEmail() != null ? usersDetails.getEmail() : existingUser.getEmail());
            existingUser.setStatus(usersDetails.getStatus() != null ? usersDetails.getStatus() : existingUser.getStatus());
            existingUser.setDni(usersDetails.getDni() != null ? usersDetails.getDni() : existingUser.getDni());
            existingUser.setRole(usersDetails.getRole() != null ? usersDetails.getRole() : existingUser.getRole());
            existingUser.setPassword(usersDetails.getPassword() != null ? usersDetails.getPassword() : existingUser.getPassword());

            Users updatedUser = usersService.updateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

 @PutMapping("/update-password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id,
                                                 @RequestParam("currentPassword") String currentPassword,
                                                 @RequestParam("newPassword") String newPassword,
                                                 @RequestParam("confirmPassword") String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
        }

        Optional<Users> userOptional = usersService.getUsersById(id);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Users user = userOptional.get();

        if (!currentPassword.equals(user.getPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(newPassword);
        usersService.updateUser(user);

        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        usersService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
