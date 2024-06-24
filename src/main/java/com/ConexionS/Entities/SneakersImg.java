package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sneakers_images")
public class SneakersImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Sneakers sneakers;
}