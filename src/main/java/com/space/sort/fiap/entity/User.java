package com.space.sort.fiap.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_user")
public class User {
    @Column(nullable = false)
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String email;
}
