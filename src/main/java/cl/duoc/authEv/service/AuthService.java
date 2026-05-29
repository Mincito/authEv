package cl.duoc.authEv.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.duoc.authEv.dto.LoginRequest;
import cl.duoc.authEv.dto.LoginResponse;
import cl.duoc.authEv.dto.UsuarioResponse;

@Service
public class AuthService {

    private final WebClient webClient;

    public AuthService(WebClient webClient) {
        this.webClient = webClient;
    }

    public LoginResponse login(LoginRequest request) {

        UsuarioResponse usuario = webClient.get()
                .uri("/correo/{correo}", request.correo())
                .retrieve()
                .bodyToMono(UsuarioResponse.class)
                .block();

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!usuario.activo()) {
            throw new RuntimeException("Usuario inactivo");
        }

        if (!usuario.contrasena().equals(request.contrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponse(
                "Login exitoso",
                usuario.id(),
                usuario.nombre(),
                usuario.rol()
        );
    }
}