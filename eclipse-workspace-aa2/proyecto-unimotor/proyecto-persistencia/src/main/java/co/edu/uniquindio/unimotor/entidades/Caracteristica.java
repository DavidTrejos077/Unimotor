package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.enterprise.inject.Typed;
import javax.persistence.*;

/**
 * Clase que implementa la entidad:Caracteristica
 *
 */
@Entity

@NamedQueries ({
	
	@NamedQuery (name="LISTA_CARACTERISTICAS",query = "select c from Caracteristica c")
	
	
})

public class Caracteristica implements Serializable {


	@Id
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;



	@ManyToMany (mappedBy = "caracteristica")
	private List <Vehiculo> vehiculo;

	/**
	 * Constructor por defecto de  caracteristica.
	 *
	 */

	public Caracteristica() {
		super();
	}  

	/**
	 * Constructor de la entidad caracteristica con todos sus atributos.
	 *
	 */

	public Caracteristica(int id, String nombre) {
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
	 * Método hash code de la entidad Caracteristica
	 */


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	/**
	 * Método equals de la entidad Caracteristica
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caracteristica other = (Caracteristica) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
