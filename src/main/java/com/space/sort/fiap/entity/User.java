package com.space.sort.fiap.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;
import org.springframework.data.annotation.Id;


@Entity
@Data
@ToString
@Table(name="tb_account")
public class User {

    @Column(nullable = false)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String email;
}
