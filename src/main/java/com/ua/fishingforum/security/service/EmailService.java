package com.ua.fishingforum.security.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    void send(String email) throws MessagingException, UnsupportedEncodingException;
}
