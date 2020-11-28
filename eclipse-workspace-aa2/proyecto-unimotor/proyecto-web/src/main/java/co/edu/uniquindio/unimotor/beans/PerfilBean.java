package co.edu.uniquindio.unimotor.beans;
/**
 * Clase para mirar las opciones registradas por el usuario en su perfil.
 */

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
@FacesConfig(version=Version.JSF_2_3)
@Named
@ViewScoped
public class PerfilBean implements Serializable {
	
	@EJB
    private UnimotorEJB clienteEJB;
	
	@Inject
	@ManagedProperty(value="#{seguridadBean.persona}")
	private Persona persona;
	
	private List <Vehiculo> misVehiculos;
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void inicializar () {
		if(persona!=null && persona.getEmail()!= null) {
			misVehiculos = clienteEJB.buscarVehiculosPorCliente(persona.getId());
		}
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Vehiculo> getMisVehiculos() {
		return misVehiculos;
	}

	public void setMisVehiculos(List<Vehiculo> misVehiculos) {
		this.misVehiculos = misVehiculos;
	}

	
	
	
	
}
