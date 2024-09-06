package org.example.Servicios.Usuario;

import org.example.Entidades.Tema.Tema;
import org.example.Entidades.Usuario.Usuario;
import org.example.Entidades.Usuario.UsuarioObservador;
import org.example.Servicios.Tema.GestorTemas;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuariosImpl implements GestorUsuarios {

    private Map<Long, UsuarioObservador> usuarios;
    private GestorTemas gestorTemas;

    public GestorUsuariosImpl(GestorTemas gestorTemas) {
        this.usuarios = new HashMap<>();
        this.gestorTemas = gestorTemas;
    }

    @Override
    public void registrarUsuario(Long id, String nombre) {
        UsuarioObservador nuevoUsuario = new Usuario(id, nombre);
        this.asociarTemasAUsuario(nuevoUsuario);
        this.usuarios.put(id, nuevoUsuario);
    }

    @Override
    public UsuarioObservador obtenerUsuario(Long id) {
        return this.usuarios.get(id);
    }

    private void asociarTemasAUsuario(UsuarioObservador nuevoUsuario) {
        Map<Long, Tema> mapeoConTodosLosTemas = this.gestorTemas.obtenerTodosLosTemas();
        for (Map.Entry<Long, Tema> entradaTema: mapeoConTodosLosTemas.entrySet()) {
            Tema tema = entradaTema.getValue();
            nuevoUsuario.suscribirATema(tema);
        }
    }
}
