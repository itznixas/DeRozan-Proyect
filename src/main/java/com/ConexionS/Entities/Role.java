package com.ConexionS.Entities;

import com.ConexionS.Entities.Users;
import com.ConexionS.Entities.userRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private userRole name;

    @OneToMany(mappedBy = "role")
    private List<Users> users;
}
