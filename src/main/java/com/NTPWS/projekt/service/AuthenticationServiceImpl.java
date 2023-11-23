package com.NTPWS.projekt.service;

import com.NTPWS.projekt.repository.interfaces.UserRepository;
import com.NTPWS.projekt.service.interfaces.AuthenticationService;
import com.NTPWS.projekt.service.interfaces.JwtService;
import com.NTPWS.projekt.command.LoginCommand;
import com.NTPWS.projekt.model.dto.LoginDTO;
import com.NTPWS.projekt.model.pojo.Authority;
import com.NTPWS.projekt.model.pojo.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository, EntityManager entityManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }


    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    @Override
    public Optional<LoginDTO> register(LoginCommand command) {
        try {
            User user = userRepository.save(mapCommandToUser(command));
            return Optional.of(
                    new LoginDTO(jwtService.createJwt(user)
                    ));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    private User mapCommandToUser(LoginCommand command) {
        return new User(command.getUsername(),
                bCryptPasswordEncoder.encode(command.getPassword()),
                Collections.singleton(entityManager.getReference(Authority.class, 2L)));
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
}
