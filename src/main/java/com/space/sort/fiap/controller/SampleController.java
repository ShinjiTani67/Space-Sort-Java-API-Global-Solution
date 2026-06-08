package com.space.sort.fiap.controller;

import com.space.sort.fiap.dto.SampleDTO;
import com.space.sort.fiap.service.SampleService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sample")
@Log
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<SampleDTO> listSample() {

        var sampleList = service.getSample();

        sampleList.forEach(s ->
                log.info("ID sample: " + s.getUuid())
        );

        return sampleList;
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<SampleDTO> update(
            @PathVariable UUID uuid,
            @RequestBody SampleDTO dto) {

        return ResponseEntity.ok(service.update(uuid, dto));
    }

    @GetMapping("/{uuid}")
    public SampleDTO getSampleById(@PathVariable UUID uuid) {
        return service.findById(uuid);
    }

    @PostMapping("/save")
    public SampleDTO saveSample(@RequestBody SampleDTO sampleDTO) {

        log.info("Salvando sample: " + sampleDTO);

        return service.save(sampleDTO);
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteSample(@PathVariable UUID uuid) {

        service.deleteById(uuid);

        return "Sample deletado com sucesso";
    }

    @GetMapping("/test")
    public String test() {
        return "Conectado com sucesso";
    }
}