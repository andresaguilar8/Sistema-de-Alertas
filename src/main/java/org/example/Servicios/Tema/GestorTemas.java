package org.example.Servicios.Tema;

import org.example.Entidades.Tema.Tema;

import java.util.Map;

public interface GestorTemas {

    void registrarTema(Long id, String descripcionTema);

    Map<Long, Tema> obtenerTodosLosTemas();

    Tema obtenerTema(Long id);
}
