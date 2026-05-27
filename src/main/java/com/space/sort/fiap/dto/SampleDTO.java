package com.space.sort.fiap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SampleDTO{

    private String name;
    private UUID uuid;
    private String description;
    private LocalDate data;
    private String owner;
}
