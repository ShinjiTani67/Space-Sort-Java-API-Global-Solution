package com.space.sort.fiap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CivilDTO {
    private String name;
    private UUID uuid;
    private String email;
    private String password;
}
