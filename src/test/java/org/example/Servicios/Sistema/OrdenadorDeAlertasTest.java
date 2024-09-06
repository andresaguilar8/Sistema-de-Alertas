package org.example.Servicios.Sistema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Alerta.AlertaInformativa;
import org.example.Entidades.Alerta.AlertaUrgente;
import org.example.Entidades.Alerta.GestorAlertas;
import org.example.Entidades.Tema.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class OrdenadorDeAlertasTest {

    private OrdenadorDeAlertas ordenadorDeAlertas;
    private GestorAlertas gestorAlertasMock;
    private Tema temaMock;
    private final int añoParaFechaNoExpirada = 2050;

    @BeforeEach
    public void setUp() {
        gestorAlertasMock= mock(GestorAlertas.class);
        temaMock = mock(Tema.class);
        ordenadorDeAlertas = new OrdenadorDeAlertas(gestorAlertasMock);
    }

    private Date crearFecha(int dia, int mes, int año) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, año);
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }

    private AlertaUrgente crearAlertaUrgente(int añoDeExpiracion) {
        AlertaUrgente alertaUrgente = new AlertaUrgente(1L, temaMock, true);
        Date fechaExpiracion = this.crearFecha(1, 1, añoDeExpiracion);
        alertaUrgente.establecerFechaExpiracion(fechaExpiracion);
        alertaUrgente.establecerFechaCreacion(new Date());
        return alertaUrgente;
    }

    private AlertaInformativa crearAlertaInformativa(int añoDeExpiracion) {
        AlertaInformativa alertaInformativa = new AlertaInformativa(1L, temaMock, true);
        Date fechaExpiracion = this.crearFecha(1, 1, añoDeExpiracion);
        alertaInformativa.establecerFechaExpiracion(fechaExpiracion);
        alertaInformativa.establecerFechaCreacion(new Date());
        return alertaInformativa;
    }

    @Test
    public void testObtenerAlertasOrdenadas() {
        int diaCreacionDeAlerta = 5;
        int mesCreacionDeAlerta = 1;
        int añoCreacionDeAlerta = 2024;

        AlertaInformativa i1 = this.crearAlertaInformativa(this.añoParaFechaNoExpirada);
        i1.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta, mesCreacionDeAlerta, añoCreacionDeAlerta));

        AlertaInformativa i2 = this.crearAlertaInformativa(this.añoParaFechaNoExpirada);
        i2.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta++, mesCreacionDeAlerta, añoCreacionDeAlerta));

        AlertaUrgente u1 = this.crearAlertaUrgente(this.añoParaFechaNoExpirada);
        u1.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta++, mesCreacionDeAlerta, añoCreacionDeAlerta));

        AlertaInformativa i3 = this.crearAlertaInformativa(this.añoParaFechaNoExpirada);
        i3.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta++, mesCreacionDeAlerta, añoCreacionDeAlerta));

        AlertaUrgente u2 = this.crearAlertaUrgente(this.añoParaFechaNoExpirada);
        u2.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta++, mesCreacionDeAlerta, añoCreacionDeAlerta));

        AlertaInformativa i4 = this.crearAlertaInformativa(this.añoParaFechaNoExpirada);
        i4.establecerFechaCreacion(crearFecha(diaCreacionDeAlerta+1, mesCreacionDeAlerta, añoCreacionDeAlerta));

        List<Alerta> listaAlertasUrgentes = new ArrayList<>();
        List<Alerta> listaAlertasInformativas = new ArrayList<>();

        listaAlertasInformativas.add(i1);
        listaAlertasInformativas.add(i2);
        listaAlertasInformativas.add(i3);
        listaAlertasInformativas.add(i4);

        listaAlertasUrgentes.add(u1);
        listaAlertasUrgentes.add(u2);

        when(gestorAlertasMock.obtenerListaAlertasUrgentes()).thenReturn(listaAlertasUrgentes);
        when(gestorAlertasMock.obtenerListaAlertasInformativas()).thenReturn(listaAlertasInformativas);

        List<Alerta> alertasOrdenadas = ordenadorDeAlertas.obtenerAlertasOrdenadas();

        assertEquals(6, alertasOrdenadas.size());

        assertEquals(u2, alertasOrdenadas.get(0));
        assertEquals(u1, alertasOrdenadas.get(1));
        assertEquals(i1, alertasOrdenadas.get(2));
        assertEquals(i2, alertasOrdenadas.get(3));
        assertEquals(i3, alertasOrdenadas.get(4));
        assertEquals(i4, alertasOrdenadas.get(5));
    }
}

