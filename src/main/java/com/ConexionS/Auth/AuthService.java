package com.ConexionS.Auth;

import com.ConexionS.Entities.Role;
import com.ConexionS.Entities.Users;
import com.ConexionS.Entities.userRole;
import com.ConexionS.Repository.UsersRepository;
import com.ConexionS.Service.UsersService;
import com.ConexionS.jwt.jwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.RequestContextFilter;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository UsersRepository;
    private final com.ConexionS.jwt.jwtService jwtService;

    public AuthResponse login(LoginAuth request){
        return null;
    }

    public AuthResponse register(RegisterAuth request){

        Role userRole = new Role();
        userRole.setName(com.ConexionS.Entities.userRole.USER);

        Users users = Users.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(userRole)
                .build();

        UsersRepository.save(users);

        return AuthResponse.builder()
                .Token(jwtService.getToken(users))
                .build();
    }
}
