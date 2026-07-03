package cl.duoc.authEv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.authEv.dto.LoginRequest;
import cl.duoc.authEv.dto.LoginResponse;
import cl.duoc.authEv.service.AuthService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Autenticación",
    description = "Operaciones relacionadas con el inicio de sesión de usuarios"
)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
        summary = "Iniciar sesión",
        description = "Valida el correo, la contraseña y el estado activo del usuario consultando usuario-service"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Inicio de sesión correcto",
            content = @Content(
                schema = @Schema(implementation = LoginResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados no son válidos"
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Correo o contraseña incorrectos"
        )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Credenciales necesarias para iniciar sesión",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = @ExampleObject(
                        name = "Ejemplo de inicio de sesión",
                        value = """
                        {
                          "correo": "juan.pinilla@correo.cl",
                          "contrasena": "123456"
                        }
                        """
                    )
                )
            )
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}