package com.space.sort.fiap.controller;

import com.space.sort.fiap.dto.AstronautDTO;
import com.space.sort.fiap.service.AstronautService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
@Log
public class AstronautController {

    private final AstronautService service;

    public AstronautController(AstronautService service) {
        this.service = service;
    }

    @GetMapping("/signin")
    public String signinPage() {
        return "signin";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "astronaut-dashboard";
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<?> listUser() {

        var userList = service.getUser();

        userList.forEach(e ->
                log.info("ID usuário: " + e.getUuid())
        );

        return ResponseEntity.ok(userList);
    }

    @ResponseBody
    @GetMapping("/{uuid}")
    public ResponseEntity<AstronautDTO> getUserById(
            @PathVariable UUID uuid) {

        return ResponseEntity.ok(
                service.findById(uuid)
        );
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<AstronautDTO> saveUser(
            @RequestBody AstronautDTO astronautDTO) {

        log.info("Salvando usuário: " + astronautDTO);

        return ResponseEntity.ok(
                service.save(astronautDTO)
        );
    }

    @ResponseBody
    @PutMapping("/{uuid}")
    public ResponseEntity<AstronautDTO> update(
            @PathVariable UUID uuid,
            @RequestBody AstronautDTO dto) {

        return ResponseEntity.ok(
                service.update(uuid, dto)
        );
    }

    @ResponseBody
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<String> deleteUser(
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