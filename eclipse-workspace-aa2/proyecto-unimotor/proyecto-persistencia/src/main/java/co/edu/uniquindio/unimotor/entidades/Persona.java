package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Implementación de la clase para la entidad:Persona
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Hay algo en la herencia que está ocasionando un error
@NamedQueries ({
	
	@NamedQuery(name = "TODAS_PERSONAS",query = "select count(p) from Persona p"),
	@NamedQuery(name = "AUTENTICAR_PERSONA",query = "select p from Persona p where p.email = :email and p.clave = :clave"),
    @NamedQuery(name= "LISTA_PERSONAS_ORDENADAS_ALFABETICAMENTE",query= "select p from Persona p order by p.nombre asc"),
	@NamedQuery(name= "LISTA_PERSONAS_USAN_CORREO_HOTMAIL",query= "select p from Persona p where p.email like '%hotmail%'"),
	@NamedQuery(name= "BUSCAR_PERSONA_POR_CORREO",query= "select p from Persona p where p.email = :email"),
	@NamedQuery(name= "BUSCAR_PERSONA_TELEFONO",query= "select p from Persona p where p.id = :telefonoFijo"),
	@NamedQuery(name="LISTA_PERSONAS",query = "select p from Persona p")
})

public class Persona implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cedula")
	private int id;
	
	@NotBlank (message = "El nombre no puede ser vacio")
	@Size(max = 100)
	@Column(name = "nombre", length=100, nullable=false)
	private String nombre;
	
	@Email
	@Size(min = 5, max = 200, message= "El email debe tener entre 5 y 200 caracteres")
	@NotBlank(message = "El email no puede ser vacio")
	@Column(name = "email", length=200 , unique = true , nullable=false)
	private String email;
	
	@NotBlank (message = "La clave no puede ser vacio")
	@Size(max=50)
	@Column(name = "clave", length=50, nullable=false)
	private String clave;
	
	@NotBlank 
	@Size(max=300)
	@Column(name = "direccion", length=300, nullable=false)
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "id_ciudad", nullable=true)
	private Ciudad ciudad;
	
	@Enumerated (EnumType.STRING)
	private Genero genero;
	
	//tenían muchos campos para los teléfonos. Con este es suficiente
	@ElementCollection
	@JoinColumn(name="telefono",nullable=true)
	private Map<String, Integer> telefonos; //este campo no puede ser null, según la restriccion que ustedes añadieron
	
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
	public int getId() {
		return this.id;
	}

	public Persona(int id, String nombre, String email, String clave, String direccion, Ciudad ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.direccion = direccion;
		this.ciudad = ciudad;
		
	}
	

	public Persona(int id, String nombre, String email, String clave, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.direccion = direccion;
		
	}

	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	
	public Map<String, Integer> getTelefonos() {
		return telefonos;
	}


	public void setTelefonos(Map<String, Integer> telefonos) {
		this.telefonos = telefonos;
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
				+ direccion + ", ciudad=" + ciudad + ", telefono=" + telefonos + ", genero=" + genero + "]";
	}
	
	
	
	
	
	
   
}
