package com.ConexionS.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUser {

    private String name;
    private String lastName;
    private String email;
}

