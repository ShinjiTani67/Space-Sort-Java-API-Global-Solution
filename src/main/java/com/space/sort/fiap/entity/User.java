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

    private String nome;

    private UUID uuid;
}
