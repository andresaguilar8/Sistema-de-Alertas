package org.example.Entidades.Usuario;


import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Tema.Tema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Usuario implements UsuarioObservador {

    private Long id;
    private String nombre;

    private List<Tema> listaDeTemasSuscritos;
    private HashMap<Alerta, Boolean> alertasRecibidas;

    public Usuario(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.listaDeTemasSuscritos = new ArrayList<>();
        this.alertasRecibidas = new HashMap<>();
    }

    @Override
    public long obtenerId() {
        return this.id;
    }

    @Override
    public String obtenerNombre() {
        return this.nombre;
    }

    @Override
    public void suscribirATema(Tema tema) {
        this.listaDeTemasSuscritos.add(tema);
        tema.suscribirUsuario(this);
    }

    @Override
    public void anularSuscripcionATema(Tema tema) {
        if (this.listaDeTemasSuscritos.contains(tema))
            listaDeTemasSuscritos.remove(tema);
    }

    @Override
    public void actualizarAlertas(Alerta nuevaAlerta) {
        this.alertasRecibidas.put(nuevaAlerta, false);
    }

    @Override
    public void marcarAlertaComoLeida(Alerta alertaAMarcar) {
        this.alertasRecibidas.put(alertaAMarcar, true);
    }

    @Override
    public HashMap<Alerta, Boolean> obtenerAlertasRecibidas() {
        return this.alertasRecibidas;
    }

    @Override
    public List<Tema> obtenerListaDeTemasSuscritos() {
        return this.listaDeTemasSuscritos;
    }




}
