
package urbanmatch.service;

import java.util.Locale;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import urbanmatch.dto.RegisterRequest;
import urbanmatch.entity.User;
import urbanmatch.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
   private final BCryptPasswordEncoder passwordEncoder;

  public UserService(
        UserRepository userRepository,
        BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

    public long countUsers() {
        return userRepository.count();
    }

    @Transactional
    public User register(RegisterRequest request) {
        String email = request.getEmail().trim()
                .toLowerCase(Locale.ROOT);

        String role = request.getRole().trim()
                .toLowerCase(Locale.ROOT);

        if (!role.equals("seeker") && !role.equals("employer")) {
            throw new IllegalArgumentException(
                    "Role must be seeker or employer");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(email);
        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setStatus("active");

        if (request.getPhone() != null
                && !request.getPhone().isBlank()) {
            user.setPhone(request.getPhone().trim());
        }

        if (request.getLocation() != null
                && !request.getLocation().isBlank()) {
            user.setLocation(request.getLocation().trim());
        }

        return userRepository.save(user);
    }
}