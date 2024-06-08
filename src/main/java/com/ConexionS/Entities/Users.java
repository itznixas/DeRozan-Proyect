package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "dni",unique = true)
    private Integer dni;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthDate", nullable = true)
    private Date birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "registrationDate", nullable = false)
    private Date registrationDate;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;
}
