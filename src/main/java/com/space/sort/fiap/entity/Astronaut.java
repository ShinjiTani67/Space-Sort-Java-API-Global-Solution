package com.space.sort.fiap.entity;


import com.space.sort.fiap.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;
import jakarta.persistence.Id;


@Entity
@Data
@ToString
@Table(name="tb_account")
public class Astronaut {

    @Column(nullable = false)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ASTRONAUT;
}
