package com.ConexionS.Service;

import com.ConexionS.Entities.Users;
import com.ConexionS.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Users createUser(Users user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  usersRepository.save(user);
    }

    public void registerUserGoogle(String email, String name, String lastname) {

        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setLastName(lastname);

        newUser.setRegistrationDate(new Date());

        newUser.setStatus("activo");

        usersRepository.save(newUser);
    }

   public List<Users> getAllUsers(){
        return usersRepository.findAll();
   }

   public Optional<Users> getUsersById(Integer id){
        return usersRepository.findById(id);
   }

   public Users updateUser(Users user){
        return usersRepository.save(user);
   }

   public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
   }

   public void deleteUserById(Integer id) {
        usersRepository.deleteById(id);
   }

    /*public boolean validateUser(String identifier, String password) {
        Optional<Users> userOptional = usersRepository.findByEmail(identifier);

        if (!userOptional.isPresent()) {
            userOptional = usersRepository.findByUsername(identifier);
        }

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }*/

}
