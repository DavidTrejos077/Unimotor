package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Clase implementada para la entidad:Fotovehiculo
 *
 */
@Entity

public class Fotovehiculo implements Serializable {

	   
	@Id
    private int id;
	private String url;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable=false)
	private Vehiculo vehiculo;
	/**
	 * Constructor por defecto de la entidad Fotovehiculo
	 */

	public Fotovehiculo() {
		super();
	}   
	
	/**
	 * Constructor  de la entidad Fotovehiculo con sus atributos
	 */
	public Fotovehiculo(int id, String url) {
		super();
		this.id = id;
		this.url = url;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Método hash code de la entidad Fotovehiculo
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Método equals de la entidad Fotovehiculo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fotovehiculo other = (Fotovehiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
