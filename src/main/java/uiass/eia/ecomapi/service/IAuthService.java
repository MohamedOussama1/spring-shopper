package uiass.eia.ecomapi.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface IAuthService {
    String createToken(String issuer, Long userId);

    DecodedJWT verifyToken(String token);
}
