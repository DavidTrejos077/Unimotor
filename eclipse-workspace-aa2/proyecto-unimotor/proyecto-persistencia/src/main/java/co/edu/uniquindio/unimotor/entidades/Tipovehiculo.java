package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que implementa la entidad:Tipovehiculo
 *
 */
@Entity

public class Tipovehiculo implements Serializable {

	   
	@Id
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "tipoVehiculo")
	private List<Vehiculo> vehiculo;

	/**
	 * Constructor por defecto de la entidad Tipovehiculo
	 */
	
	public Tipovehiculo() {
		super();
	}  
	
	/**
	 * Constructor de la entidad Tipovehiculo con sus atributos
	 */
	public Tipovehiculo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * Método hash code de la entidad Tipovehiculo
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	
	/**
	 * Método hash code de la entidad Tipovehiculo
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipovehiculo other = (Tipovehiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
