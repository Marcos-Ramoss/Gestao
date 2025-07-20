package com.service.setebit.gestao.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.setebit.gestao.adapter.dto.AuthLoginRequest;
import com.service.setebit.gestao.adapter.dto.AuthRegisterRequest;
import com.service.setebit.gestao.adapter.dto.AuthRegisterResponse;
import com.service.setebit.gestao.adapter.dto.AuthResponse;
import com.service.setebit.gestao.application.service.CustomUserDetailsService;
import com.service.setebit.gestao.application.service.JwtService;
import com.service.setebit.gestao.infrastructure.entity.UserEntity;
import com.service.setebit.gestao.infrastructure.repository.UserJpaRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                         CustomUserDetailsService userDetailsService,
                         JwtService jwtService,
                         UserJpaRepository userRepository,
                         PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        // Recupera o nome do usuário autenticado
        String nome = "";
        if (userDetails instanceof UserEntity userEntity) {
            nome = userEntity.getNome();
        } else {
            // Busca o usuário no banco de dados pelo username
            var userOptional = userRepository.findByUsername(userDetails.getUsername());
            if (userOptional.isPresent()) {
                nome = userOptional.get().getNome();
            }
        }

        return ResponseEntity.ok(new AuthResponse(nome, token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> register(@RequestBody AuthRegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if (userRepository.findAll().stream().anyMatch(u -> u.getCpf().equals(request.cpf()))) {
            return ResponseEntity.badRequest().build();
        }
        UserEntity user = UserEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .nome(request.nome())
                .cpf(request.cpf())
                .build();
        userRepository.save(user);
        AuthRegisterResponse response = new AuthRegisterResponse(
            user.getUsername(),
            user.getNome(),
            user.getCpf()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil")
    public ResponseEntity<AuthRegisterResponse> getPerfil(@AuthenticationPrincipal UserDetails userDetails) {
        var userOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = userOptional.get();
        AuthRegisterResponse response = new AuthRegisterResponse(
            user.getUsername(),
            user.getNome(),
            user.getCpf()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/perfil")
    public ResponseEntity<AuthRegisterResponse> updatePerfil(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AuthRegisterRequest request) {
        var userOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity user = userOptional.get();
        user.setNome(request.nome());
        user.setCpf(request.cpf());
        userRepository.save(user);
        AuthRegisterResponse response = new AuthRegisterResponse(
            user.getUsername(),
            user.getNome(),
            user.getCpf()
        );
        return ResponseEntity.ok(response);
    }
}
