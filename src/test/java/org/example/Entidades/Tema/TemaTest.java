package org.example.Entidades.Tema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Usuario.UsuarioObservador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TemaTest {

    private Tema tema;
    private UsuarioObservador usuarioMock;
    private Alerta alertaMock;

    @BeforeEach
    public void setUp() {
        tema = new Tema(1L, "");
        usuarioMock = Mockito.mock(UsuarioObservador.class);
        alertaMock = Mockito.mock(Alerta.class);
        tema.agregarAlerta(alertaMock);
    }

    @Test
    public void testSuscribirUsuario() {
        tema.suscribirUsuario(usuarioMock);

        List<UsuarioObservador> usuariosSuscritos = tema.obtenerListaDeUsuariosSuscritos();

        assertTrue(usuariosSuscritos.contains(usuarioMock));
    }

    @Test
    public void testEliminarUsuarioSuscrito() {
        tema.suscribirUsuario(usuarioMock);
        tema.eliminarUsuarioSuscrito(usuarioMock);

        List<UsuarioObservador> usuariosSuscritos = tema.obtenerListaDeUsuariosSuscritos(); // Asegúrate de que el nombre del método sea correcto

        assertFalse(usuariosSuscritos.contains(usuarioMock));
    }

    @Test
    public void testEnviarAlertaAUsuariosSuscritos() {
        UsuarioObservador usuario1 = Mockito.mock(UsuarioObservador.class);
        UsuarioObservador usuario2 = Mockito.mock(UsuarioObservador.class);
        tema.suscribirUsuario(usuario1);
        tema.suscribirUsuario(usuario2);

        Alerta alerta = Mockito.mock(Alerta.class);

        tema.enviarAlertaAUsuariosSuscritos(alerta);

        verify(usuario1, times(1)).actualizarAlertas(alerta);
        verify(usuario2, times(1)).actualizarAlertas(alerta);
    }

    @Test
    public void testEnviarAlertaAUsuario() {
        UsuarioObservador usuario = Mockito.mock(UsuarioObservador.class);
        Alerta alerta = Mockito.mock(Alerta.class);

        tema.enviarAlertaAUsuario(alerta, usuario);
        verify(usuario, times(1)).actualizarAlertas(alerta);
    }

}
