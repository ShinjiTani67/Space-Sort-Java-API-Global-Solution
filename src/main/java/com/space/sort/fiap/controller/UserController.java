package com.space.sort.fiap.controller;

import com.space.sort.fiap.dto.SampleDTO;
import com.space.sort.fiap.dto.UserDTO;
import com.space.sort.fiap.service.SampleService;
import com.space.sort.fiap.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@Log
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<UserDTO> listUser() {

        var userList = service.getUser();

        userList.forEach(e ->
                log.info("ID funcionário: " + e.getUuid())
        );

        return userList;
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserDTO> update(
            @PathVariable UUID uuid,
            @RequestBody UserDTO dto) {

        return ResponseEntity.ok(service.update(uuid, dto));
    }

    @GetMapping("/{uuid}")
    public UserDTO getUserById(@PathVariable UUID uuid) {
        return service.findById(uuid);
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {

        log.info("Salvando funcionário: " + userDTO);

        return service.save(userDTO);
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteUser(@PathVariable UUID uuid) {

        service.deleteById(uuid);

        return "Funcionário deletado com sucesso";
    }

    @GetMapping("/test")
    public String test() {
        return "Conectado com sucesso";
    }
}
