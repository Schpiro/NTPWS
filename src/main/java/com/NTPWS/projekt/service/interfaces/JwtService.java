package com.NTPWS.projekt.service.interfaces;

import com.NTPWS.projekt.model.pojo.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
