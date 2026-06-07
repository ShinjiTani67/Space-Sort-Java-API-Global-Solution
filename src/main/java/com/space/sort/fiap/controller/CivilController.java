package com.space.sort.fiap.controller;

import com.space.sort.fiap.dto.CivilDTO;
import com.space.sort.fiap.service.CivilService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/civils")
@Log
public class CivilController {

    private final CivilService service;

    public CivilController(CivilService service) {
        this.service = service;
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "civil-dashboard";
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<?> listCivil() {

        var civilList = service.getCivil();

        civilList.forEach(e ->
                log.info("ID usuário: " + e.getUuid())
        );

        return ResponseEntity.ok(civilList);
    }

    @ResponseBody
    @GetMapping("/{uuid}")
    public ResponseEntity<CivilDTO> getCivilById(
            @PathVariable UUID uuid) {

        return ResponseEntity.ok(
                service.findById(uuid)
        );
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<CivilDTO> saveCivil(
            @RequestBody CivilDTO civilDTO) {

        log.info("Salvando usuário: " + civilDTO);

        return ResponseEntity.ok(
                service.save(civilDTO)
        );
    }

    @ResponseBody
    @PutMapping("/{uuid}")
    public ResponseEntity<CivilDTO> update(
            @PathVariable UUID uuid,
            @RequestBody CivilDTO dto) {

        return ResponseEntity.ok(
                service.update(uuid, dto)
        );
    }

    @ResponseBody
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteCivil(
            @PathVariable UUID uuid) {

        service.deleteById(uuid);

        return ResponseEntity.ok(
                "Usuário deletado com sucesso"
        );
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Conectado com sucesso";
    }
}
