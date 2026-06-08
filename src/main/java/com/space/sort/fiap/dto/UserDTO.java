package com.space.sort.fiap.dto;

import com.space.sort.fiap.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String name;
    private UUID uuid;
    private String email;
    private String password;
    private Role role;
}
