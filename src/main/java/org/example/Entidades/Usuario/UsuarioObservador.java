package org.example.Entidades.Usuario;


import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Tema.Tema;

import java.util.HashMap;
import java.util.List;

public interface UsuarioObservador {

    long obtenerId();

    String obtenerNombre();

    void suscribirATema(Tema tema);

    void anularSuscripcionATema(Tema tema);

    void actualizarAlertas(Alerta nuevaAlerta);

    void marcarAlertaComoLeida(Alerta alerta);

    HashMap<Alerta, Boolean> obtenerAlertasRecibidas();

    List<Tema> obtenerListaDeTemasSuscritos();




}
