package org.example.Entidades.Usuario;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Tema.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioTest {

    private UsuarioObservador usuario;
    private Tema temaMock;
    private Alerta alertaMock;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario(1L, "Nombre usuario");
        temaMock = Mockito.mock(Tema.class);
        alertaMock = Mockito.mock(Alerta.class);
    }

    @Test
    public void testObtenerNombre() {
        assertEquals("Nombre usuario", usuario.obtenerNombre());
        assertEquals(1L, usuario.obtenerId());
    }

    @Test
    public void testMarcarAlertaComoLeida() {
        usuario.marcarAlertaComoLeida(alertaMock);

        HashMap<Alerta, Boolean> alertasRecibidas = usuario.obtenerAlertasRecibidas();

        assertTrue(alertasRecibidas.containsKey(alertaMock));
        assertTrue(alertasRecibidas.get(alertaMock));
    }

    @Test
    public void testSuscribirATema() {
        usuario.suscribirATema(temaMock);

        List<Tema> listaDeTemasSuscritos = usuario.obtenerListaDeTemasSuscritos();

        assertTrue(listaDeTemasSuscritos.contains(temaMock));
        verify(temaMock, times(1)).suscribirUsuario(usuario);
    }

    @Test
    public void testAnularSuscripcionATema() {
        usuario.suscribirATema(temaMock);
        usuario.anularSuscripcionATema(temaMock);

        List<Tema> listaDeTemasSuscritos = usuario.obtenerListaDeTemasSuscritos();
        assertFalse(listaDeTemasSuscritos.contains(temaMock));
    }

    @Test
    public void testActualizarAlertas() {
        usuario.actualizarAlertas(alertaMock);

        HashMap<Alerta, Boolean> alertasRecibidas = usuario.obtenerAlertasRecibidas();

        assertTrue(alertasRecibidas.containsKey(alertaMock));
        assertFalse(alertasRecibidas.get(alertaMock));
    }
}
