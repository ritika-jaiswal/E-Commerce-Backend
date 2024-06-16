package com.ecommerce.service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public User findUserByJwtToken(String jwt) throws Exception {
        // Ensure the JWT string is correctly formatted
        if (jwt == null || !jwt.startsWith("Bearer ") || jwt.split("\\.").length != 3) {
            throw new MalformedJwtException("Invalid JWT token");
        }

        // Extract the email from the JWT
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        // Find the user by the extracted email
        User user = findUserByEmail(email);

        return user;
    }

    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
