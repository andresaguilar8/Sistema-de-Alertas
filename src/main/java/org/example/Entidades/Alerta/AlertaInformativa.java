package org.example.Entidades.Alerta;

import org.example.Entidades.Alerta.AlertaVisitor.AlertaVisitor;
import org.example.Entidades.Tema.Tema;

public class AlertaInformativa extends Alerta {

    public AlertaInformativa(Long id, Tema temaAsociado, boolean esParaTodosLosUsuarios) {
        super(id, temaAsociado, esParaTodosLosUsuarios);
    }

    @Override
    public void aceptar(AlertaVisitor alertaVisitor) {
        alertaVisitor.visitar(this);
    }
}
