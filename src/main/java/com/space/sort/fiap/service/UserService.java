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

    private UserDTO convertToDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setUuid(user.getUuid());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        return dto;
    }

    private User convertToEntity(UserDTO dto) {

        User user = new User();

        if (dto.getUuid() != null) {
            user.setUuid(dto.getUuid());
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }

    public UserDTO save(UserDTO userDTO) {

        User user = convertToEntity(userDTO);

        // NÃO gerar UUID manualmente
        user = repository.save(user);

        return convertToDTO(user);
    }

    // LISTAR TODOS
    public List<UserDTO> getUser() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public UserDTO findById(UUID uuid) {

        User user = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(user);
    }

    public UserDTO findByEmail(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário com email " + email + " não encontrado"
                        )
                );

        return convertToDTO(user);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Usuário com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public UserDTO update(UUID uuid, UserDTO dto) {

        User user = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        user = repository.save(user);

        return convertToDTO(user);
    }
}