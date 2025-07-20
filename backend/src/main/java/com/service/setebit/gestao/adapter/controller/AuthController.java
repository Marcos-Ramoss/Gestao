package com.service.setebit.gestao.adapter.controller;

import org.springframework.http.HttpStatus;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsável pela autenticação e registro de usuários.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "API para autenticação e registro de usuários")
@Slf4j
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
    @Operation(summary = "Login do usuário", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content)
    })
    public ResponseEntity<AuthResponse> login(
            @Parameter(description = "Dados de login", required = true)
            @Valid @RequestBody AuthLoginRequest request) {
        log.info("Tentativa de login para usuário: {}", request.username());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        String nome = "";
        if (userDetails instanceof UserEntity userEntity) {
            nome = userEntity.getNome();
        } else {
            var userOptional = userRepository.findByUsername(userDetails.getUsername());
            if (userOptional.isPresent()) {
                nome = userOptional.get().getNome();
            }
        }
        log.info("Login realizado com sucesso para usuário: {}", request.username());
        return ResponseEntity.ok(new AuthResponse(nome, token));
    }

    /**
     * Realiza o registro de um novo usuário.
     *
     * @param request Dados de registro
     * @return Dados do usuário registrado
     */
    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Registra um novo usuário no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso", content = @Content(schema = @Schema(implementation = AuthRegisterResponse.class))),
        @ApiResponse(responseCode = "400", description = "Usuário ou CPF já cadastrado", content = @Content)
    })
    public ResponseEntity<AuthRegisterResponse> register(
            @Parameter(description = "Dados de registro", required = true)
            @Valid @RequestBody AuthRegisterRequest request) {
        log.info("Tentativa de registro para usuário: {}", request.username());
        if (userRepository.findByUsername(request.username()).isPresent()) {
            log.warn("Usuário já cadastrado: {}", request.username());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (userRepository.findAll().stream().anyMatch(u -> u.getCpf().equals(request.cpf()))) {
            log.warn("CPF já cadastrado: {}", request.cpf());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
        log.info("Usuário registrado com sucesso: {}", user.getUsername());
        return ResponseEntity.ok(response);
    }

    /**
     * Retorna o perfil do usuário autenticado.
     *
     * @param userDetails Dados do usuário autenticado
     * @return Dados do perfil
     */
    @GetMapping("/perfil")
    @Operation(summary = "Obter perfil do usuário", description = "Retorna os dados do perfil do usuário autenticado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perfil retornado com sucesso", content = @Content(schema = @Schema(implementation = AuthRegisterResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<AuthRegisterResponse> getPerfil(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        log.info("Consulta de perfil para usuário: {}", userDetails.getUsername());
        var userOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userOptional.isEmpty()) {
            log.warn("Usuário não encontrado: {}", userDetails.getUsername());
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

    /**
     * Atualiza o perfil do usuário autenticado.
     *
     * @param userDetails Dados do usuário autenticado
     * @param request Novos dados do perfil
     * @return Perfil atualizado
     */
    @PutMapping("/perfil")
    @Operation(summary = "Atualizar perfil do usuário", description = "Atualiza os dados do perfil do usuário autenticado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso", content = @Content(schema = @Schema(implementation = AuthRegisterResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<AuthRegisterResponse> updatePerfil(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter(description = "Novos dados do perfil", required = true)
            @Valid @RequestBody AuthRegisterRequest request) {
        log.info("Atualização de perfil para usuário: {}", userDetails.getUsername());
        var userOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userOptional.isEmpty()) {
            log.warn("Usuário não encontrado: {}", userDetails.getUsername());
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
        log.info("Perfil atualizado com sucesso para usuário: {}", user.getUsername());
        return ResponseEntity.ok(response);
    }
}
