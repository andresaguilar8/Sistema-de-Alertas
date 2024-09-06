package org.example.Servicios.Tema;

import org.example.Entidades.Tema.Tema;

import java.util.HashMap;
import java.util.Map;

public class GestorTemasImpl implements GestorTemas {

    private Map<Long, Tema> temas;

    public GestorTemasImpl() {
        this.temas = new HashMap<>();
    }

    @Override
    public void registrarTema(Long id, String descripcionTema) {
        Tema nuevoTema = new Tema(id, descripcionTema);
        this.temas.put(id, nuevoTema);
    }

    @Override
    public Map<Long, Tema> obtenerTodosLosTemas() {
        return this.temas;
    }

    @Override
    public Tema obtenerTema(Long id) {
        return this.temas.get(id);
    }
}
