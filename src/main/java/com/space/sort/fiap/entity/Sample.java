package com.space.sort.fiap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="tb_user")
public class Sample {
    @Column(nullable = false)
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private String nome;
}
