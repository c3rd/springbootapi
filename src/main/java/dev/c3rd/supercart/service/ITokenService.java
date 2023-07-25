package dev.c3rd.supercart.service;

import dev.c3rd.supercart.model.User;

public interface ITokenService {
    String generateToken(User user);
    String validateToken(String token);
}
