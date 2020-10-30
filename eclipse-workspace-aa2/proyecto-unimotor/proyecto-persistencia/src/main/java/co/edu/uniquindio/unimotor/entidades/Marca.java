package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Clase implementada para la entidad:Marca
 *
 */
@Entity

@NamedQueries ({
	
})

public class Marca implements Serializable {

	   
	@Id
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "marca")
	private List<Modelo> modelo;
	
	@OneToMany(mappedBy = "marca")
	private List<Vehiculo> vehiculo;

	
	/**
	 * Constructor por defecto de la clase Marca
	 */
	public Marca() {
		super();
	}  
	
	/**
	 * Constructor de la clase Marca con todos sus atributos
	 */
	public Marca(int id, String nombre) {
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
	 * Método hash code de la entidad Marca
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	
	/**
	 * Método equals de la entidad Marca
	 */


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
