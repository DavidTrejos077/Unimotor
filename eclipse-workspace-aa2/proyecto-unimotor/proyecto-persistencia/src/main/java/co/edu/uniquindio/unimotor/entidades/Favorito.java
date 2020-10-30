package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase implementada para la entidad:Favorito
 *
 */
@Entity
@NamedQueries ({
	
	@NamedQuery(name = "LISTA__PERSONA_FAVORITOS",query = "select f.vehiculo from Favorito f where f.persona.email = :email")
})

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
	
	public Favorito(Persona persona, Vehiculo vehiculo) {
		super();
		this.persona = persona;
		this.vehiculo = vehiculo;
	}

	


	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getId() {
		return this.id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método hash code de la entidad Favorito
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * Método equals de la entidad Favorito
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
