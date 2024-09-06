package org.example.Entidades.Alerta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GestorAlertaTest {

    private GestorAlertas gestorAlertas;
    private AlertaUrgente alertaUrgente;
    private AlertaInformativa alertaInformativa;

    @BeforeEach
    public void setUp() {
        gestorAlertas = new GestorAlertas();
        alertaUrgente = mock(AlertaUrgente.class);
        alertaInformativa = mock(AlertaInformativa.class);
    }

    @Test
    public void testAgregarAlertaInformativa() {
        gestorAlertas.agregarAlertaInformativa(alertaInformativa);
        assertTrue(gestorAlertas.obtenerListaAlertasInformativas().contains(alertaInformativa));
    }

    @Test
    public void testAgregarAlertaUrgente() {
        gestorAlertas.agregarAlertaUrgente(alertaUrgente);
        assertTrue(gestorAlertas.obtenerListaAlertasUrgentes().contains(alertaUrgente));
    }

    @Test
    public void testVaciarListasDeAlertas() {
        gestorAlertas.agregarAlertaInformativa(alertaInformativa);
        gestorAlertas.agregarAlertaUrgente(alertaUrgente);

        gestorAlertas.vaciarListasDeAlertas();

        assertTrue(gestorAlertas.obtenerListaAlertasInformativas().isEmpty());
        assertTrue(gestorAlertas.obtenerListaAlertasUrgentes().isEmpty());
    }

}
