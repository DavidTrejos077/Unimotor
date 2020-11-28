package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
/**
 * Clase que contiene las propiedades en detalle  del vehiculo. 
 */

@Named
@ViewScoped
public class DetalleVehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UnimotorEJB unimotorEJB;
	private Vehiculo vehiculo;
	
	@NotBlank
	private String pregunta;
	private Boolean favorito;
	
	@Inject
	@ManagedProperty(value="#{param.vehiculo}")
	private String proyectoParam;
	
	private List <Pregunta> preguntas;
	private List <Caracteristica> caracteristicas;
	
	@Inject
	@ManagedProperty(value="#{seguridadBean.persona}")
	private Persona persona;
	
	@PostConstruct
	
	public void inicializar () {
		if(proyectoParam!= null && !proyectoParam.isEmpty()) {
			
			try {
				int codigoV = Integer.parseInt(proyectoParam);
				vehiculo=unimotorEJB.obtenerVehiculo(codigoV);
				preguntas = unimotorEJB.obtenerPreguntasVehiculo(codigoV);
				caracteristicas= unimotorEJB.obtenerCaracteristicasVehiculo(codigoV);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método para hacer una pregunta.
	 */

	
	public void hacerPregunta () {
		
		try {
			
		
		Pregunta p = unimotorEJB.hacerPregunta(persona, vehiculo, pregunta);
		if (p!= null) {
			preguntas.add(p);
		  }
		pregunta="";
		}catch (Exception e) {
			FacesMessage m= new FacesMessage (FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
		    FacesContext.getCurrentInstance().addMessage(null, m);
		} 
	}
	
	/**
	 * Método para marcar como favorito.
	 */

	
	public void marcarComoFavorito() throws Exception {
		if (favorito) {
			unimotorEJB.guardarVehiculoComoFavorito(vehiculo, persona) ;
			
			
		}else {
			
			unimotorEJB.eliminarVehiculoComoFavorito(vehiculo, persona);
		}
	}

	

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}



}





