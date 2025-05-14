package emt.lab.web.rest;

import emt.lab.dto.display.DisplayAuthLogDto;
import emt.lab.model.domain.AuthRequest;
import emt.lab.model.domain.User;
import emt.lab.repository.UserRepository;
import emt.lab.service.application.AuthLogApplicationService;
import emt.lab.service.application.impl.AuthLogApplicationServiceImpl;
import emt.lab.service.domain.impl.JwtServiceImpl;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthLogApplicationService authLogApplicationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
    @GetMapping("/user/me")
    public ResponseEntity<User> getMyUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/logs")
    public List<DisplayAuthLogDto> getAllLogs(){
        return authLogApplicationService.findAll();
    }
}
