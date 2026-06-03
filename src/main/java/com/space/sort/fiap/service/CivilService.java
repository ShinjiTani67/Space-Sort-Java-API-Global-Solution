package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.CivilDTO;
import com.space.sort.fiap.entity.Civil;
import com.space.sort.fiap.repository.CivilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CivilService {

    private final CivilRepository repository;

    private CivilDTO convertToDTO(Civil civil) {

        CivilDTO dto = new CivilDTO();

        dto.setUuid(civil.getUuid());
        dto.setName(civil.getName());

        return dto;
    }

    private Civil convertToEntity(CivilDTO dto) {

        Civil civil = new Civil();

        if (dto.getUuid() != null) {
            civil.setUuid(dto.getUuid());
        }

        civil.setName(dto.getName());
        civil.setEmail(dto.getEmail());
        civil.setUuid(dto.getUuid());

        return civil;
    }

    public CivilDTO save(CivilDTO civilDTO) {

        Civil civil = convertToEntity(civilDTO);

        civil = repository.save(civil);

        return convertToDTO(civil);
    }

    public List<CivilDTO> getCivil() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CivilDTO findById(UUID uuid) {

        Civil civil = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(civil);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Funcionário com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public CivilDTO update(UUID uuid, CivilDTO dto) {

        Civil civil = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        civil.setName(dto.getName());
        civil.setEmail(dto.getEmail());

        civil = repository.save(civil);

        return convertToDTO(civil);
    }
}
