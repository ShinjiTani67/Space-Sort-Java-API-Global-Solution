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
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
