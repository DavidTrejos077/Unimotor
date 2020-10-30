package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que implementa la entidad:Tipocombustible
 *
 */
@Entity

public class Tipocombustible implements Serializable {

	   
	@Id
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;
	


	
	/**
	 * Constructor por defecto de la entidad Tipocombustible
	 */

	public Tipocombustible() {
		super();
	}  
	
	
	/**
	 * Constructor de la entidad Tipocombustible con sus atributos
	 */
	
	public Tipocombustible(int id, String nombre) {
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
	 * Método hash code de la entidad Tipocombustible
	 */
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	
	/**
	 * Método equals de la entidad Tipocombustible
	 */


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipocombustible other = (Tipocombustible) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
   
}
