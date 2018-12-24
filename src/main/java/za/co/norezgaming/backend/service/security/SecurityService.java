package za.co.norezgaming.backend.service.security;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    public UUID getAuthentication(){
        return UUID.randomUUID();
    }
}
