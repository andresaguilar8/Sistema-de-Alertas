package org.example.Servicios.Sistema;

import org.example.Entidades.Alerta.Alerta;
import org.example.Entidades.Tema.Tema;
import org.example.Entidades.Usuario.Usuario;

import java.util.List;
import java.util.Map;

public interface SistemaDeAlertas {

    void crearAlerta(Long id, Tema temaSociado, boolean esParaTodosLosUsuarios);

    Map<Long, Alerta> obtenerTodasLasAlertas();

    void enviarAlertaSobreUnTemaAUsuario(Alerta alerta, Usuario usuario);

    void enviarAlertaSobreUnTemaAUsuariosSuscritos(Alerta alerta);

    List<Alerta> obtenerAlertasNoExpiradasNoLeidasDeUnUsuario(Usuario usuario);

    List<Alerta> obtenerAlertasNoExpiradasDeUnTema(Tema tema);


}
