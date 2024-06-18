package com.ConexionS.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shoppingBag")
public class ShoppingBag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_shoppingBag;

    @Column(name = "amount", nullable = false)
    private String amount;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private  Users users;

    @OneToMany(mappedBy = "shoppingBag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingBagItem> items = new ArrayList<>();
}
