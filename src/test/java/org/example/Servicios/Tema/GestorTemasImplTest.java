package org.example.Servicios.Tema;

import org.example.Entidades.Tema.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class GestorTemasImplTest {

    private GestorTemasImpl gestorTemas;

    @BeforeEach
    void setUp() {
        gestorTemas = new GestorTemasImpl();
    }

    @Test
    void testRegistrarTema() {
        Long temaId = 1L;
        String descripcion = "Tecnología";

        gestorTemas.registrarTema(temaId, descripcion);

        Tema tema = gestorTemas.obtenerTema(temaId);
        assertNotNull(tema);
        assertEquals(temaId, tema.obtenerId());
        assertEquals(descripcion, tema.obtenerDescripcion());
    }

    @Test
    void testObtenerTodosLosTemas() {
        gestorTemas.registrarTema(1L, "Tecnología");
        gestorTemas.registrarTema(2L, "Ciencia");

        Map<Long, Tema> temas = gestorTemas.obtenerTodosLosTemas();

        assertEquals(2, temas.size());
        assertNotNull(temas.get(1L));
        assertNotNull(temas.get(2L));
    }

    @Test
    void testObtenerTema() {
        Long temaId = 1L;
        String descripcion = "Deportes";

        gestorTemas.registrarTema(temaId, descripcion);
        Tema tema = gestorTemas.obtenerTema(temaId);

        assertNotNull(tema);
        assertEquals(temaId, tema.obtenerId());
        assertEquals(descripcion, tema.obtenerDescripcion());
    }
}

