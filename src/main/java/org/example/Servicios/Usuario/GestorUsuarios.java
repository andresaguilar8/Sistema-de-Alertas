package org.example.Servicios.Usuario;

import org.example.Entidades.Usuario.UsuarioObservador;

public interface GestorUsuarios {

    void registrarUsuario(Long id, String nombre);

    UsuarioObservador obtenerUsuario(Long id);
}
