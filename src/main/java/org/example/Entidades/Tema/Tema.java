package org.example.Entidades.Tema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Usuario.UsuarioObservador;
import java.util.LinkedList;
import java.util.List;

public class Tema {

    private Long id;
    private String descripcionTema;
    private List<Alerta> listaDeAlertas;
    private List<UsuarioObservador> usuariosSuscritos;

    public Tema(Long id, String descripcionTema) {
        this.id = id;
        this.descripcionTema = descripcionTema;
        this.usuariosSuscritos = new LinkedList<>();
        this.listaDeAlertas = new LinkedList<>();
    }

    public Long obtenerId() {
        return this.id;
    }

    public String obtenerDescripcion() {
        return this.descripcionTema;
    }

    public List<Alerta> obtenerListaDeAlertas() {
        return this.listaDeAlertas;
    }

    public List<UsuarioObservador> obtenerListaDeUsuariosSuscritos() {
        return this.usuariosSuscritos;
    }

    public void suscribirUsuario(UsuarioObservador usuarioASuscribir) {
        this.usuariosSuscritos.add(usuarioASuscribir);
    }

    public void eliminarUsuarioSuscrito(UsuarioObservador usuarioAEliminar) {
        this.usuariosSuscritos.remove(usuarioAEliminar);
    }

    public void enviarAlertaAUsuariosSuscritos(Alerta alertaAEnviar) {
        for(UsuarioObservador usuarioANotificar: this.usuariosSuscritos) {
            usuarioANotificar.actualizarAlertas(alertaAEnviar);
        }
    }

    public void enviarAlertaAUsuario(Alerta alertaAEnviar, UsuarioObservador usuarioAEnviarAlerta) {
        usuarioAEnviarAlerta.actualizarAlertas(alertaAEnviar);
    }

    public List<Alerta> obtenerAlertas() {
        return this.listaDeAlertas;
    }

    public void agregarAlerta(Alerta alerta) {
        this.listaDeAlertas.add(alerta);
    }

}
