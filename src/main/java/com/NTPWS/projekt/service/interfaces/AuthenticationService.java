package com.NTPWS.projekt.service.interfaces;

import com.NTPWS.projekt.command.LoginCommand;
import com.NTPWS.projekt.model.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

    Optional<LoginDTO> register(LoginCommand command);
}
