package co.edu.uniquindio.unimotor.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Ciudad;

import co.edu.uniquindio.unimotor.entidades.Genero;
import co.edu.uniquindio.unimotor.entidades.Persona;

/**
 * Clase para registrar un usuario.
 */


@Named
@ViewScoped
public class UsuarioBean implements Serializable  {

	

	private static final long serialVersionUID = 1L;

	@EJB
	private UnimotorEJB unimotor;

	private Persona persona;
	
	private List<SelectItem> listaCiudades = new ArrayList<SelectItem>();
	
	private String genero;

	private String tipoPersona;

	 @PostConstruct
    public void init() {
		 
		System.out.println("init");
		Ciudad ciudadPersona = new Ciudad();
		persona = new Persona();
		persona.setCiudad(ciudadPersona);
		List<Ciudad> listaCiudadesDB =  unimotor.obtenerListaCiudades();
		for(Ciudad temp: listaCiudadesDB) {
			SelectItem select = new SelectItem();
			select.setLabel(temp.getNombre());
			select.setValue(String.valueOf(temp.getId()));
			listaCiudades.add(select);
		}
	}
	 
	 
	 /**
		 * Método para registrar.
		 */

	 
	public void registrar () {

		try {

			//Acá pasa lo mismo que con el vehículo, no pueden crear objetos y asignarlos porque esos objetos
			//que crea acá no existen en la bd
	
			//Debe crear un método que retorne una ciudad dado un código
			
			System.out.println("selección: "+tipoPersona);

			

				if (genero.equals("Masculino")) {
					persona.setGenero(Genero.MASCULINO);
				}else {
					persona.setGenero(Genero.FEMENINO);;
				}

				unimotor.registrarPersona(persona);
				

				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Registro Exitoso!");
				FacesContext.getCurrentInstance().addMessage(null, msj);



			

		} catch (Exception e) {

			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
		}
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getTipoPersona() {
		return tipoPersona;
	}


	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public List<SelectItem> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<SelectItem> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	
}
