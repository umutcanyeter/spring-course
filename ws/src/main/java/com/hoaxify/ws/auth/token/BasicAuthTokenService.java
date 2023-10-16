package com.hoaxify.ws.auth.token;

import com.hoaxify.ws.auth.dto.Credentials;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService{
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Token createToken(User user, Credentials creds) {
        String emailColonPassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if(authorizationHeader == null) return null;
        var base64Encoded = authorizationHeader.split("Basic ")[1];
        var decoded = new String(Base64.getDecoder().decode(base64Encoded));
        var credentials = decoded.split(":");
        var email = credentials[0];
        var password = credentials[1];
        User inDb = userService.findByEmail(email);
        if(inDb == null) return null;
        if(!passwordEncoder.matches(password, inDb.getPassword())) return null;
        return inDb;
    }
}
