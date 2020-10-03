package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Implementación de la clase para la entidad:Persona
 *
 */
@Entity

public class Persona implements Serializable {

	   
	@Id
	@Column(name = "cedula")
	private int id;
	
	@Column(name = "nombre", length=100, nullable=false)
	private String nombre;
	
	@Column(name = "email", length=200 , unique = true , nullable=false)
	private String email;
	
	@Column(name = "clave", length=50, nullable=false)
	private String clave;
	
	@Column(name = "direccion", length=300, nullable=false)
	private String direccion;
	
	
	@ManyToOne
	@JoinColumn(name = "id_ciudad", nullable=false)
	private Ciudad ciudad;
	
	@OneToMany(mappedBy = "persona")
	private List<Telefono> telefono;

	@OneToMany(mappedBy = "persona")
	private List<Favorito> favorito;
	
	@OneToMany(mappedBy = "persona")
	private List<Pregunta> pregunta;
	
	@OneToMany(mappedBy = "persona")
	private List<Vehiculo> vehiculo;
	
	@Enumerated (EnumType.STRING)
	private Genero genero;
	
	
	
	@Max(150)
	@Column(name = "telefonoFijo", nullable=false)
	private int telefonoFijo;
	
	
	@ElementCollection
	@JoinColumn(nullable=false)
	private Map<String, Integer> telefonos;
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructor por defecto de  persona.
	 *
	 */

	public Persona() {
		super();
	}   
	
	
	/**
	 * Constructor de la entidad persona con todos sus atributos.
	 *
	 */
	
	public Persona(int id, String nombre, String email, String clave, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.direccion = direccion;
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
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
	/**
	 * Método hash code de la entidad Persona
	 */

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * Método equals de la entidad Persona
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	/**
	 * Método toString de la entidad Persona
	 */

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", clave=" + clave + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", telefono=" + telefono + ", genero=" + genero + ", telefonoFijo="
				+ telefonoFijo + "]";
	}
	
	
	
   
}
