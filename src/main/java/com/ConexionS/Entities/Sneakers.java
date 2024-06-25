package com.ConexionS.Entities;


import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sneakers")
public class Sneakers {

    @Id
    private Integer id_sneakers;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "size", nullable = false)
    private Double size;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "registration", nullable = false)
    private Date registration;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_IconicLine", nullable = false)
    private IconicLine iconicLine;
}