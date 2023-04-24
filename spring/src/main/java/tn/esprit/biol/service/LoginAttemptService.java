package tn.esprit.biol.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginAttemptService {
    private final static int MAX_ATTEMPTS = 3;
    private final Map<String, Integer> attemptsCache = new HashMap<>();

    public void addUserToLoginAttemptCache(String username) {
        System.out.println("Connexion echoué");
        int attempts = attemptsCache.getOrDefault(username, 0);
        attempts++;
        attemptsCache.put(username, attempts);
    }

    public boolean hasExceededMaxAttempts(String username) {
        return attemptsCache.getOrDefault(username, 0) >= MAX_ATTEMPTS;
    }

    public void evictUserFromLoginAttemptCache(String username) {

        attemptsCache.remove(username);
    }
}
