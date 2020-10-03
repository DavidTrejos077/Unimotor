package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase implementada para la entidad:Favorito
 *
 */
@Entity

public class Favorito implements Serializable {

	   
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "id_persona", nullable=false)
	private Persona persona;
	
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable=false)
	private Vehiculo vehiculo;
	
	/**
	 * Constructor por defecto de la entidad favorito
	 *
	 */

	public Favorito() {
		super();
	}   
	
	/**
	 * Constructor de la entidad favorito con su atributos
	 *
	 */
	
	public Favorito(int id) {
		super();
		this.id = id;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * M�todo hash code de la entidad Favorito
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * M�todo equals de la entidad Favorito
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorito other = (Favorito) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
   
}
