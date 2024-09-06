package org.example.Entidades.Alerta;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Alerta.AlertaInformativa;
import org.example.Entidades.Alerta.AlertaUrgente;
import org.example.Entidades.Alerta.AlertaVisitor.AlertaVisitorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

public class AlertaVisitorImplTest {

    private AlertaVisitorImpl alertaVisitor;
    private AlertaInformativa alertaInformativaMock;
    private AlertaUrgente alertaUrgenteMock;
    private GestorAlertas gestorAlertasMock;

    @BeforeEach
    public void setUp() {
        alertaInformativaMock = mock(AlertaInformativa.class);
        alertaUrgenteMock = mock(AlertaUrgente.class);
        gestorAlertasMock = mock(GestorAlertas.class);
        alertaVisitor = new AlertaVisitorImpl(gestorAlertasMock);

    }

    @Test
    public void testVisitarAlertaInformativa() {
        alertaVisitor.visitar(alertaInformativaMock);
        verify(gestorAlertasMock, times(1)).agregarAlertaInformativa(alertaInformativaMock);
    }

    @Test
    public void testVisitarAlertaUrgente() {
        alertaVisitor.visitar(alertaUrgenteMock);
        verify(gestorAlertasMock, times(1)).agregarAlertaUrgente(alertaUrgenteMock);

    }


}

