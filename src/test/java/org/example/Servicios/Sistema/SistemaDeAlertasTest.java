package org.example.Servicios.Sistema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Alerta.AlertaVisitor.AlertaVisitor;
import org.example.Entidades.Alerta.GestorAlertas;
import org.example.Entidades.Tema.Tema;
import org.example.Entidades.Usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.mockito.Mockito.*;


public class SistemaDeAlertasTest {

    private SistemaDeAlertas sistemaDeAlertas;
    private AlertaVisitor alertaVisitor;
    private GestorAlertas gestorAlertasMock;
    private OrdenadorDeAlertas ordenadorDeAlertas;

    private Tema temaMock;
    private Usuario usuarioMock;
    private Alerta alertaMock;

    @BeforeEach
    void setUp() {

        gestorAlertasMock = mock(GestorAlertas.class);
        ordenadorDeAlertas = mock(OrdenadorDeAlertas.class);
        alertaVisitor = mock(AlertaVisitor.class);
        sistemaDeAlertas = new SistemaDeAlertasImpl(alertaVisitor, gestorAlertasMock, ordenadorDeAlertas);
        temaMock = mock(Tema.class);
        usuarioMock = mock(Usuario.class);
        alertaMock = mock(Alerta.class);
    }

    @Test
    public void testEnviarAlertaSobreUnTemaAUsuario() {
        when(alertaMock.obtenerTemaAsociado()).thenReturn(temaMock);
        sistemaDeAlertas.enviarAlertaSobreUnTemaAUsuario(alertaMock, usuarioMock);
        verify(temaMock, times(1)).enviarAlertaAUsuario(alertaMock, usuarioMock);
    }

    @Test
    public void testEnviarAlertaSobreUnTemaAUsuariosSuscritos() {
        when(alertaMock.obtenerTemaAsociado()).thenReturn(temaMock);
        sistemaDeAlertas.enviarAlertaSobreUnTemaAUsuariosSuscritos(alertaMock);
        verify(temaMock, times(1)).enviarAlertaAUsuariosSuscritos(alertaMock);
    }

    @Test
    public void testObtenerAlertasNoExpiradasNoLeidasDeUnUsuario() {
        HashMap<Alerta, Boolean> alertasRecibidasDeUnUsuario = new HashMap<>();
        Alerta alertaMockLeida = mock(Alerta.class);
        alertasRecibidasDeUnUsuario.put(alertaMock, false);
        alertasRecibidasDeUnUsuario.put(alertaMockLeida, true);

        when(usuarioMock.obtenerAlertasRecibidas()).thenReturn(alertasRecibidasDeUnUsuario);
        when(alertaMock.estaExpirada()).thenReturn(false);
        when(alertaMockLeida.estaExpirada()).thenReturn(false);

        sistemaDeAlertas.obtenerAlertasNoExpiradasNoLeidasDeUnUsuario(usuarioMock);

        verify(gestorAlertasMock, times(1)).vaciarListasDeAlertas();
        verify(alertaMock, times(1)).aceptar(alertaVisitor);
        verify(alertaMockLeida, times(0)).aceptar(alertaVisitor);
        verify(ordenadorDeAlertas, times(1)).obtenerAlertasOrdenadas();
    }

    @Test
    public void testObtenerAlertasNoExpiradasDeUnTema() {
        List<Alerta> listaDeAlertas = new ArrayList<>();
        Alerta alertaMockExpirada = mock(Alerta.class);

        listaDeAlertas.add(alertaMock);
        listaDeAlertas.add(alertaMockExpirada);

        when(temaMock.obtenerListaDeAlertas()).thenReturn(listaDeAlertas);
        when(alertaMock.estaExpirada()).thenReturn(false);
        when(alertaMockExpirada.estaExpirada()).thenReturn(true);

        sistemaDeAlertas.obtenerAlertasNoExpiradasDeUnTema(temaMock);

        verify(gestorAlertasMock, times(1)).vaciarListasDeAlertas();
        verify(alertaMock, times(1)).aceptar(alertaVisitor);
        verify(alertaMockExpirada, times(0)).aceptar(alertaVisitor);
        verify(ordenadorDeAlertas, times(1)).obtenerAlertasOrdenadas();
    }
}
