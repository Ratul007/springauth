package com.example.springauth.service;

import com.example.springauth.model.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}