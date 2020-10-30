package co.edu.uniquindio.unimotor.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class HolaMundoBean {

	private String mensaje = "Inicia sesion en Unimotor";
	
	private String mensaje1 = "Unimotor";
	
	
	

	
	

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje1() {
		return mensaje1;
	}

	public void setMensaje1(String mensaje1) {
		this.mensaje1 = mensaje1;
	}
	
}
