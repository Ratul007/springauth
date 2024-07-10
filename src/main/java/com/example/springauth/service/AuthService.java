package com.example.springauth.service;
import com.example.springauth.model.LoginDto;
import com.example.springauth.model.Role;
import com.example.springauth.model.SignupDto;
import com.example.springauth.model.User;
import com.example.springauth.repository.RoleRepository;
import com.example.springauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public interface AuthServiceLogin {
        String login(LoginDto loginDto);
    }

    public void signup(SignupDto signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        // Assuming all new users will have ROLE_USER by default
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role ROLE_USER is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);
    }
}
