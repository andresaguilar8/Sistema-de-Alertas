package org.example.Entidades.Alerta.AlertaVisitor;

import org.example.Entidades.Alerta.AlertaInformativa;
import org.example.Entidades.Alerta.AlertaUrgente;
import org.example.Entidades.Alerta.GestorAlertas;

public class AlertaVisitorImpl implements AlertaVisitor {

    private GestorAlertas gestorAlertas;

    public AlertaVisitorImpl(GestorAlertas gestorAlertas) {
        this.gestorAlertas = gestorAlertas;
    }

    @Override
    public void visitar(AlertaInformativa alertaInformativa) {
        gestorAlertas.agregarAlertaInformativa(alertaInformativa);
    }

    @Override
    public void visitar(AlertaUrgente alertaUrgente) {
        gestorAlertas.agregarAlertaUrgente(alertaUrgente);
    }
}
