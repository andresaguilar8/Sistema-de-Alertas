package org.example.Entidades.Alerta;

import org.example.Entidades.Alerta.AlertaVisitor.AlertaVisitor;
import org.example.Entidades.Tema.Tema;

import java.util.Date;

public abstract class Alerta {

    private Long id;
    private String contenido;

    private Date fechaCreacion;

    private Date fechaExpiracion;
    private boolean esParaTodosLosUsuarios;
    private Tema temaAsociado;

    public Alerta(Long id, Tema temaAsociado, boolean esParaTodosLosUsuarios) {
        this.id = id;
        this.temaAsociado = temaAsociado;
        this.esParaTodosLosUsuarios = esParaTodosLosUsuarios;
    }

    public void establecerFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public void establecerFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Date obtenerFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public Date obtenerFechaCreacion() {
        return this.fechaCreacion;
    }

    public boolean estaExpirada() {
        if (this.fechaExpiracion != null) {
            Date fechaActual = new Date();
            return fechaActual.after(this.fechaExpiracion);
        }
        else
            return false;
    }

    public String obtenerContenido() {
        return this.contenido;
    }

    public abstract void aceptar(AlertaVisitor alertaVisitor);


    public Tema obtenerTemaAsociado() {
        return this.temaAsociado;
    }
}
