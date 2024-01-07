package uiass.eia.ecomapi.service;

import java.security.Key;
import java.security.KeyPair;

public interface IKeyService {
    KeyPair generateKeyPair();
}
