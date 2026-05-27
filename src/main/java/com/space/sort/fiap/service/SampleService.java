package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.SampleDTO;
import com.space.sort.fiap.entity.Sample;
import com.space.sort.fiap.repository.SampleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SampleService {

    private final SampleRepository repository;

    private SampleDTO convertToDTO(Sample sample) {

        SampleDTO dto = new SampleDTO();

        dto.setUuid(sample.getUuid());
        dto.setName(sample.getName());
        dto.setDescription(sample.getDescription());
        dto.setDate(sample.getDate());
        dto.setOwner(sample.getOwner());

        return dto;
    }

    private Sample convertToEntity(SampleDTO dto) {

        Sample sample = new Sample();

        // Só define UUID se for atualização
        if (dto.getUuid() != null) {
            sample.setUuid(dto.getUuid());
        }

        sample.setName(dto.getName());
        sample.setDescription(dto.getDescription());
        sample.setUuid(dto.getUuid());
        sample.setDate(dto.getDate());
        sample.setOwner(dto.getOwner());

        return sample;
    }

    public SampleDTO save(SampleDTO sampleDTO) {

        Sample sample = convertToEntity(sampleDTO);

        sample = repository.save(sample);

        return convertToDTO(sample);
    }

    // LISTAR TODOS
    public List<SampleDTO> getSample() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public SampleDTO findById(UUID uuid) {

        Sample sample = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(sample);
    }

    public SampleDTO findByEmail(String email) {

        Sample sample = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com email " + email + " não encontrado"
                        )
                );

        return convertToDTO(sample);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Funcionário com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public SampleDTO update(UUID uuid, SampleDTO dto) {

        Sample sample = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        sample.setNome(dto.getNome());
        sample.setEmail(dto.getEmail());
        sample.setSenha(dto.getSenha());
        sample.setCargo(dto.getCargo());

        sample = repository.save(sample);

        return convertToDTO(sample);
    }
}
