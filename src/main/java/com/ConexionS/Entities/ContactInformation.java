package com.ConexionS.Entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contactInformation")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactId;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, unique = true )
    private  Users users;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "prefix", nullable = false)
    private Integer prefix;

    @Column(name = "phone", nullable = false)
    private Integer phone;
}
