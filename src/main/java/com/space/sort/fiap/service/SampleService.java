package com.space.sort.fiap.service;

import com.space.sort.fiap.dto.SampleDTO;
import com.space.sort.fiap.entity.Sample;
import com.space.sort.fiap.repository.SampleRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        dto.setData(sample.getDate());
        dto.setOwner(sample.getOwner());

        return dto;
    }

    private Sample convertToEntity(SampleDTO dto) {

        Sample sample = new Sample();

        if (dto.getUuid() != null) {
            sample.setUuid(dto.getUuid());
        }

        sample.setName(dto.getName());
        sample.setDescription(dto.getDescription());
        sample.setOwner(dto.getOwner());
        sample.setDate(dto.getData());
        sample.setUuid(dto.getUuid());
        return sample;
    }

    public SampleDTO save(SampleDTO sampleDTO) {

        Sample sample = convertToEntity(sampleDTO);
        sample = repository.save(sample);

        return convertToDTO(sample);
    }

    public List<SampleDTO> getSample() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SampleDTO findById(UUID uuid) {

        Sample sample = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Sample com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(sample);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Sample com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public SampleDTO update(UUID uuid, SampleDTO dto) {

        Sample sample = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Sample não encontrado"));

        sample.setUuid(dto.getUuid());
        sample.setName(dto.getName());
        sample.setDescription(dto.getDescription());
        sample.setDate(dto.getData());
        sample.setOwner(dto.getOwner());

        sample = repository.save(sample);

        return convertToDTO(sample);
    }
}