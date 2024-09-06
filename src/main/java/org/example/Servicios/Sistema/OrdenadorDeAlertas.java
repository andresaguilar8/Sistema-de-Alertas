package org.example.Servicios.Sistema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Alerta.GestorAlertas;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenadorDeAlertas {

    private GestorAlertas gestorAlertas;

    public OrdenadorDeAlertas(GestorAlertas gestorAlertas) {
        this.gestorAlertas = gestorAlertas;
    }

    public List<Alerta> obtenerAlertasOrdenadas() {
        List<Alerta> listaAlertasUrgentes = gestorAlertas.obtenerListaAlertasUrgentes();
        List<Alerta> listaAlertasInformativas = gestorAlertas.obtenerListaAlertasInformativas();
        this.ordenarListaSegunFechaCreacionAlerta(listaAlertasUrgentes);
        return this.concatenarListas(listaAlertasUrgentes, listaAlertasInformativas);
    }

    private void ordenarListaSegunFechaCreacionAlerta(List<Alerta> listaAOrdenar) {
        listaAOrdenar.sort(Comparator.comparing(Alerta::obtenerFechaCreacion).reversed());
    }

    private List<Alerta> concatenarListas(List<Alerta> listaUno, List<Alerta> listaDos) {
        List<Alerta> listaResultante = new ArrayList<>(listaUno);
        listaResultante.addAll(listaDos);
        return listaResultante;
    }

}
