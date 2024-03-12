package com.ua.fishingforum.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public record RsaKeys(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) {
}
