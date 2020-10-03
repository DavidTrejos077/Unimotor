package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Implementación de la clase para la entidad:Ciudad
 *
 */
@Entity

public class Ciudad implements Serializable {

	   
	@Id
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "ciudad")
	private List<Persona> personas;
	
	@OneToMany(mappedBy = "ciudad")
	private List<Vehiculo> vehiculo;
	/**
	 * Constructor por defecto de la entidad Ciudad.
	 *
	 */

	public Ciudad() {
		super();
	}   
	
	/**
	 * Constructor de la entidad Ciudad con sus respectivos atributos.
	 *
	 */
	
	public Ciudad(int id, String nombre) {
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
	 * Método hash code de la entidad Ciudad
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Método equals de la entidad Ciudad
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", nombre=" + nombre + ", personas=" + personas + ", vehiculo=" + vehiculo + "]";
	}
	
	
   
}
