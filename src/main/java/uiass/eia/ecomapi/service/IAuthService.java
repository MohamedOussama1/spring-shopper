package uiass.eia.ecomapi.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPublicKey;

public interface IAuthService {
    String createToken(String issuer, Long userId);

    DecodedJWT verifyToken(String token);
}
