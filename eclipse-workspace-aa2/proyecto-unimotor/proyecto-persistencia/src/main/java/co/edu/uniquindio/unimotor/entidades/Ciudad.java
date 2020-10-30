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

@NamedQueries ({
	
	@NamedQuery(name = "LISTADO_PERSONAS_CIUDAD",query = "select p.nombre, p.email, p.direccion from Ciudad c join c.personas p where c.nombre = :nombre"),
	@NamedQuery (name = "CIUDAD_CON_MAS_VEHICULOS_PARA_VENTA",query = "select max (c.nombre) from Ciudad c join c.vehiculo v "),
	@NamedQuery(name = "LISTA_CIUDADES",query = "select c from Ciudad c")
})

public class Ciudad implements Serializable {

	//Si no van a poner literalmente el id, entonces debe ser autogenerado
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
