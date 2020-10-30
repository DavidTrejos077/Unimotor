package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que implementa la entidad: Modelo
 *
 */
@Entity

@NamedQueries ({
	
	@NamedQuery (name="LISTA_MODELOS",query = "select m from Modelo m")
	
	
})


public class Modelo implements Serializable {

	   
	@Id
	@Column(name = "id_modelo")
	private int id;
	private String nombre;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_marca", nullable=false)
	private Marca marca;
	
	@OneToMany(mappedBy = "modelo")
	private List<Vehiculo> vehiculo;

	
	/**
	 * Constructor por defecto para la entidad modelo.
	 *
	 */
	
	public Modelo() {
		super();
	}   
	
	/**
	 * Constructor de la entidad modelo con todos sus atributos.
	 *
	 */
	
	
	public Modelo(int id, String nombre) {
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
	 * Método hashcode de la entidad Modelo.
	 *
	 */


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	
	/**
	 * Método equals de la entidad Modelo.
	 *
	 */


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
   
}
