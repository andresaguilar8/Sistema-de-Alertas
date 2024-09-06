package org.example.Entidades.Alerta.AlertaVisitor;

import org.example.Entidades.Alerta.AlertaInformativa;
import org.example.Entidades.Alerta.AlertaUrgente;

public interface AlertaVisitor {

    void visitar(AlertaInformativa alertaInformativa);

    void visitar(AlertaUrgente alertaUrgente);

}
