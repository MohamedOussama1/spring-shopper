package uiass.eia.ecomapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uiass.eia.ecomapi.utils.FileUtils;

import java.io.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;


 @Component
public class AuthService implements IAuthService{
     IKeyService iKeyService = new RSAKeyService();
     KeyPair keyPair = iKeyService.generateKeyPair();
     RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
     RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
     @Override
     public String createToken(String issuer, Long userId) {
         String token = null;
         try {
             Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
             token = JWT.create()
                     .withIssuer(issuer)
                     .withClaim("subject", userId)
                     .sign(algorithm);
         } catch (JWTCreationException exception){
             exception.printStackTrace();
         }
         System.out.println(token);
         return token;
     }
     public DecodedJWT verifyToken(String token) {
         DecodedJWT decodedJWT = null;
         try {
             Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
             JWTVerifier verifier = JWT.require(algorithm)
                     // specify an specific claim validations
                     .withIssuer("auth0")
                     // reusable verifier instance
                     .build();

             decodedJWT = verifier.verify(token);
         } catch (JWTVerificationException exception){
             // Invalid signature/claims
         }
         return decodedJWT;
     }
}
