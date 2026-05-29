package cl.duoc.authEv.dto;

public record UsuarioResponse(
    int id,
    String nombre,
    String correo,
    String contrasena,
    String rol,
    String telefono,
    boolean activo
) {
}