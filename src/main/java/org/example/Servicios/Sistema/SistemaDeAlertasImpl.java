package org.example.Servicios.Sistema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Alerta.AlertaInformativa;
import org.example.Entidades.Alerta.AlertaVisitor.AlertaVisitor;
import org.example.Entidades.Alerta.GestorAlertas;
import org.example.Entidades.Tema.Tema;
import org.example.Entidades.Usuario.Usuario;
import java.util.List;
import java.util.Map;

public class SistemaDeAlertasImpl implements SistemaDeAlertas {

    private AlertaVisitor alertaVisitor;
    private OrdenadorDeAlertas ordenadorDeAlertas;
    private GestorAlertas gestorAlertas;
    private Map<Long, Alerta> todasLasAlertas;

    public SistemaDeAlertasImpl(AlertaVisitor alertaVisitor, GestorAlertas gestorAlertas, OrdenadorDeAlertas ordenadorDeAlertas) {
        this.gestorAlertas = gestorAlertas;
        this.ordenadorDeAlertas = ordenadorDeAlertas;
        this.alertaVisitor = alertaVisitor;
    }

    @Override
    public void crearAlerta(Long id, Tema temaSociado, boolean esParaTodosLosUsuarios) {
        Alerta nuevaAlerta = new AlertaInformativa(id, temaSociado, esParaTodosLosUsuarios);
        temaSociado.agregarAlerta(nuevaAlerta);
        todasLasAlertas.put(id, nuevaAlerta);
    }

    @Override
    public Map<Long, Alerta> obtenerTodasLasAlertas() {
        return this.todasLasAlertas;
    }

    @Override
    public void enviarAlertaSobreUnTemaAUsuario(Alerta alertaAEnviar, Usuario usuarioAEnviarAlerta) {
        alertaAEnviar.obtenerTemaAsociado().enviarAlertaAUsuario(alertaAEnviar, usuarioAEnviarAlerta);
    }

    @Override
    public void enviarAlertaSobreUnTemaAUsuariosSuscritos(Alerta alertaAEnviar) {
        alertaAEnviar.obtenerTemaAsociado().enviarAlertaAUsuariosSuscritos(alertaAEnviar);
    }

    @Override
    public List<Alerta> obtenerAlertasNoExpiradasNoLeidasDeUnUsuario(Usuario usuario) {
        this.gestorAlertas.vaciarListasDeAlertas();

        for (Map.Entry<Alerta, Boolean> entrada: usuario.obtenerAlertasRecibidas().entrySet()) {
            Alerta alerta = entrada.getKey();
            boolean alertaLeida = entrada.getValue();
            if (!alerta.estaExpirada() && !alertaLeida) {
                alerta.aceptar(this.alertaVisitor);
            }
        }

        return this.ordenadorDeAlertas.obtenerAlertasOrdenadas();
    }

    @Override
    public List<Alerta> obtenerAlertasNoExpiradasDeUnTema(Tema tema) {
        this.gestorAlertas.vaciarListasDeAlertas();

        for (Alerta alerta: tema.obtenerListaDeAlertas()) {
            if (!alerta.estaExpirada()) {
                alerta.aceptar(this.alertaVisitor);
            }
        }

        return this.ordenadorDeAlertas.obtenerAlertasOrdenadas();
    }

}
