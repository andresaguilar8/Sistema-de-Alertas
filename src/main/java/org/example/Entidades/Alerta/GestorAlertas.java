package org.example.Entidades.Alerta;

import java.util.ArrayList;
import java.util.List;

public class GestorAlertas {

    private List<Alerta> listaAlertasUrgentes;
    private List<Alerta> listaAlertasInformativas;

    public GestorAlertas() {
        this.listaAlertasUrgentes = new ArrayList<>();
        this.listaAlertasInformativas = new ArrayList<>();
    }

    public void vaciarListasDeAlertas() {
        this.listaAlertasInformativas.clear();
        this.listaAlertasUrgentes.clear();
    }

    public List<Alerta> obtenerListaAlertasUrgentes() {
        return this.listaAlertasUrgentes;
    }

    public List<Alerta> obtenerListaAlertasInformativas() {
        return this.listaAlertasInformativas;
    }

    public void agregarAlertaInformativa(AlertaInformativa alertaInformativa) {
        this.listaAlertasInformativas.add(alertaInformativa);
    }

    public void agregarAlertaUrgente(AlertaUrgente alertaUrgente) {
        this.listaAlertasUrgentes.add(alertaUrgente);
    }
}
