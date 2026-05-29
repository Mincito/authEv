package cl.duoc.authEv.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @NotBlank(message = "Correo no puede ser inexistente") String correo,

    @NotBlank(message = "Tiene que haber una contraseña") String contrasena

) {
}