package com.space.sort.fiap.controller;

import com.space.sort.fiap.dto.SampleDTO;
import com.space.sort.fiap.service.SampleService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/samples")
@Log
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<?> listSample() {

        var sampleList = service.getSample();

        sampleList.forEach(e ->
                log.info("ID sample: " + e.getUuid())
        );

        return ResponseEntity.ok(sampleList);
    }

    @ResponseBody
    @GetMapping("/{uuid}")
    public ResponseEntity<SampleDTO> getSampleById(
            @PathVariable UUID uuid) {

        return ResponseEntity.ok(
                service.findById(uuid)
        );
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<SampleDTO> saveSample(
            @RequestBody SampleDTO sampleDTO) {

        log.info("Salvando sample: " + sampleDTO);

        return ResponseEntity.ok(
                service.save(sampleDTO)
        );
    }

    @ResponseBody
    @PutMapping("/{uuid}")
    public ResponseEntity<SampleDTO> update(
            @PathVariable UUID uuid,
            @RequestBody SampleDTO dto) {

        return ResponseEntity.ok(
                service.update(uuid, dto)
        );
    }

    @ResponseBody
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteSample(
            @PathVariable UUID uuid) {

        service.deleteById(uuid);

        return ResponseEntity.ok(
                "Sample deletado com sucesso"
        );
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Conectado com sucesso";
    }
}