package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_role;

    @Column(name = "name", nullable = false)
    private String name;

    public Role(Integer id_role) {
        this.id_role = id_role;
    }
}
