package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.AstronautDTO;
import com.space.sort.fiap.entity.Astronaut;
import com.space.sort.fiap.repository.AstronautRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final AstronautRepository repository;

    private AstronautDTO convertToDTO(Astronaut astronaut) {

        AstronautDTO dto = new AstronautDTO();

        dto.setUuid(astronaut.getUuid());
        dto.setName(astronaut.getName());

        
        return dto;
    }

    private Astronaut convertToEntity(AstronautDTO dto) {

        Astronaut astronaut = new Astronaut();

        if (dto.getUuid() != null) {
            astronaut.setUuid(dto.getUuid());
        }

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());
        astronaut.setUuid(dto.getUuid());
        return astronaut;
    }

    public AstronautDTO save(AstronautDTO astronautDTO) {

        Astronaut astronaut = convertToEntity(astronautDTO);

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }

    public List<AstronautDTO> getUser() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public AstronautDTO findById(UUID uuid) {

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

    public AstronautDTO update(UUID uuid, AstronautDTO dto) {

        Astronaut astronaut = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        astronaut.setName(dto.getName());
        astronaut.setEmail(dto.getEmail());

        astronaut = repository.save(astronaut);

        return convertToDTO(astronaut);
    }
}