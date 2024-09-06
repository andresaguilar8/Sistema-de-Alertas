package org.example.Servicios.Usuario;

import org.example.Entidades.Tema.Tema;
import org.example.Entidades.Usuario.UsuarioObservador;
import org.example.Servicios.Tema.GestorTemas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GestorUsuariosImplTest {

    private GestorTemas gestorTemasMock;
    private GestorUsuariosImpl gestorUsuarios;

    @BeforeEach
    void setUp() {
        gestorTemasMock = Mockito.mock(GestorTemas.class);
        gestorUsuarios = new GestorUsuariosImpl(gestorTemasMock);
    }

    @Test
    void testRegistrarUsuario() {
        Long usuarioId = 1L;
        String nombreUsuario = "Juan";
        Map<Long, Tema> temasMock = new HashMap<>();
        Tema temaMock = mock(Tema.class);
        temasMock.put(1L, temaMock);

        when(gestorTemasMock.obtenerTodosLosTemas()).thenReturn(temasMock);

        gestorUsuarios.registrarUsuario(usuarioId, nombreUsuario);

        UsuarioObservador nuevoUsuario = gestorUsuarios.obtenerUsuario(usuarioId);
        assertEquals(usuarioId, nuevoUsuario.obtenerId());
        assertEquals(nombreUsuario, nuevoUsuario.obtenerNombre());
        verify(temaMock, times(1)).suscribirUsuario(nuevoUsuario);
    }
}
