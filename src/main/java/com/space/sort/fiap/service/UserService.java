package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.UserDTO;
import com.space.sort.fiap.entity.Astronaut;
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

    private UserDTO convertToDTO(Astronaut astronaut) {

        UserDTO dto = new UserDTO();

        dto.setUuid(astronaut.getUuid());
        dto.setName(astronaut.getName());

        
        return dto;
    }

    private Astronaut convertToEntity(UserDTO dto) {

        Astronaut astronaut = new Astronaut();

        if (dto.getUuid() != null) {
            astronaut.setUuid(dto.getUuid());
        }

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());
        astronaut.setUuid(dto.getUuid());
        return astronaut;
    }

    public UserDTO save(UserDTO userDTO) {

        Astronaut astronaut = convertToEntity(userDTO);

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }

    public List<UserDTO> getUser() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public UserDTO findById(UUID uuid) {

        Astronaut astronaut = repository.findById(uuid)
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

        Astronaut astronaut = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }
}