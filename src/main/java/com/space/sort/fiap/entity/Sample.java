package com.space.sort.fiap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Id;

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
    private LocalDate date;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String owner;
}
