package co.edu.uniquindio.unimotor.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Caracteristica;

public class CaracteristicaConverter implements Converter<Caracteristica>, Serializable {

	@EJB
	private UnimotorEJB unimotorEJB;
	private static final long serialVersionUID = 1L;
	@Override
	public Caracteristica getAsObject(FacesContext context, UIComponent component, String value) {
		Caracteristica car = null;
		 if(value!=null) {
			 car=unimotorEJB.obtenerCaracteristica(Integer.parseInt(value));
		 }
		 return car;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Caracteristica value) {
		if(value!=null) {
			return String.format("%d", value.getId ());
		}
		return"";
	}

}
