package com.ConexionS.Entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ratings;

    @Column(name = "qualification",  nullable = false)
    private Integer qualification;

    @Column(name = "comments",  nullable = false)
    private String comments;

    @Column(name = "registration",  nullable = false)
    private Date registration;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true )
    private  Users users;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false, unique = true )
    private  Sneakers sneakers;
}
