package com.space.sort.fiap.service;

import com.space.sort.fiap.entity.Astronaut;
import com.space.sort.fiap.entity.Civil;
import com.space.sort.fiap.repository.AstronautRepository;
import com.space.sort.fiap.repository.CivilRepository;
import com.space.sort.fiap.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService
        implements org.springframework.security.core.userdetails.UserDetailsService {

    private final CivilRepository civilRepository;
    private final AstronautRepository astronautRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        System.out.println("Tentando login com: " + email);

        Optional<Civil> civil = civilRepository.findByEmail(email);

        if (civil.isPresent()) {

            System.out.println("Civil encontrado");
            System.out.println("Senha banco: " + civil.get().getPassword());
            System.out.println("Role banco: " + civil.get().getRole());

            return new UserDetailsImpl(
                    civil.get().getEmail(),
                    civil.get().getPassword(),
                    civil.get().getRole()
            );
        }

        Optional<Astronaut> astronaut =
                astronautRepository.findByEmail(email);

        if (astronaut.isPresent()) {

            System.out.println("Astronauta encontrado");
            System.out.println("Senha banco: " + astronaut.get().getPassword());
            System.out.println("Role banco: " + astronaut.get().getRole());

            return new UserDetailsImpl(
                    astronaut.get().getEmail(),
                    astronaut.get().getPassword(),
                    astronaut.get().getRole()
            );
        }

        System.out.println("Usuário não encontrado");

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}