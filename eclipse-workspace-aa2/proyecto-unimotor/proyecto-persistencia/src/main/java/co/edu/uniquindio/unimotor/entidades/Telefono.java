package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Clase que implementa la entidad:Telefono
 *
 */
@Entity

public class Telefono implements Serializable {

	   
	@Id
	private int id;
	private String clave;
	private int numero;
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name ="id_persona", nullable=false)
	private Persona persona;
	
/**
 * Constructor por defecto de la entidad Telefono
 */
	public Telefono() {
		super();
	}   
	
	/**
	 * Constructor de la entidad Telefono con todos sus atributos
	 */
	public Telefono(int id, String clave, int numero) {
		super();
		this.id = id;
		this.clave = clave;
		this.numero = numero;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}   
	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Método hashcode de la entidad Telefono
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * Método equals de la entidad Telefono
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefono other = (Telefono) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
