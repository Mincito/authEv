package cl.duoc.authEv.dto;

public record LoginResponse(
    String mensaje,
    int idUsuario,
    String nombre,
    String rol
) {
}