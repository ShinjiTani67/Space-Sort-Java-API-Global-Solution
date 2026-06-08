package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.UserDTO;
import com.space.sort.fiap.entity.User;
import com.space.sort.fiap.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private UserDTO convertToDTO(User astronaut) {

        UserDTO dto = new UserDTO();

        dto.setUuid(astronaut.getUuid());
        dto.setName(astronaut.getName());
        dto.setEmail(astronaut.getEmail());
        dto.setPassword(astronaut.getPassword());
        dto.setRole(astronaut.getRole());

        return dto;
    }

    private User convertToEntity(UserDTO dto) {

        User astronaut = new User();

        if (dto.getUuid() != null) {
            astronaut.setUuid(dto.getUuid());
        }

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());
        astronaut.setUuid(dto.getUuid());
        astronaut.setPassword(dto.getPassword());
        astronaut.setRole(dto.getRole());
        return astronaut;
    }

    public UserDTO save(UserDTO userDTO) {

        User astronaut = convertToEntity(userDTO);

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }

    public List<UserDTO> getUser() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(UUID uuid) {

        User astronaut = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(astronaut);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Funcionário com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public UserDTO update(UUID uuid, UserDTO dto) {

        User astronaut = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());
        astronaut.setPassword(dto.getPassword());
        astronaut.setRole(dto.getRole());

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }
}