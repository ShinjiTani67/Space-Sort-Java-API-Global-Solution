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

        Optional<Civil> civil = civilRepository.findByEmail(email);

        if (civil.isPresent()) {
            return new UserDetailsImpl(
                    civil.get().getEmail(),
                    civil.get().getPassword(),
                    civil.get().getRole()
            );
        }

        Optional<Astronaut> astronaut =
                astronautRepository.findByEmail(email);

        if (astronaut.isPresent()) {
            return new UserDetailsImpl(
                    astronaut.get().getEmail(),
                    astronaut.get().getPassword(),
                    astronaut.get().getRole()
            );
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}