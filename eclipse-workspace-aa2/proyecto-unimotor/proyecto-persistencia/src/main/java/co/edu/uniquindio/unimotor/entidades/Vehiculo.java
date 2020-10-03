package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Clase que implementa la entidad:Vehiculo
 *
 */
@Entity 
public class Vehiculo implements Serializable {

	   
	@Id
	@Column(name = "id_vehiculo", length=12)
	private int id;
	
	@Column(name = "precio", nullable=false)
	private float precio;
	
	@Column(name = "descripcion", length=200, nullable=false)
	private String descripcion;
	
	@Column(name = "color", length=25, nullable=false)
	private String color;
	
	@Column(name = "anio", nullable=false)
	private int anio;
	
	@Column(name = "cilindraje", nullable=false)
	private int cilindraje;
	
	@Max(5)
	@Min(1)
	@Column(name = "numeropuertas", nullable=false)
	private Integer numeroPuertas;
	private static final long serialVersionUID = 1L;
	
	@Enumerated (EnumType.STRING)
	private TipoCombustibleEnum tipocombustible;
	

	@Enumerated (EnumType.STRING)
	private TipovehiculoEnum tipovehiculo;
	
	@ManyToOne
	@JoinColumn(name = "id_persona", nullable=false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "id_ciudad", nullable=false)
	private Ciudad ciudad;
	
	@ManyToOne
	@JoinColumn(name = "id_marca", nullable=false)
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "id_tipovehiculo", nullable=false)
	private Tipovehiculo tipoVehiculo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_tipocombustible", nullable=false)
	private Tipocombustible tipoCombustible;
	
	
	@OneToMany(mappedBy = "vehiculo")
	@JoinColumn(nullable=true)
	private List<Favorito> favorito;
	
	
	@OneToMany(mappedBy = "vehiculo")
	@JoinColumn(nullable=true)
	private List<Pregunta> pregunta;
	
	
	
	@OneToMany(mappedBy = "vehiculo")
	private List<Fotovehiculo> fotoVehiculo;
	
	@ManyToMany 
	private List <Caracteristica> caracteristica;
	
	
	
	
	
	
	
	/**
	 * Constructor por defecto de  persona.
	 *
	 */
	
	
	public Vehiculo() {
		super();
	} 
	
	
	
	
	


	
	

	/**
	 * Constructor del vehiculo con todos sus atributos.
	 *
	 */

	public Vehiculo(int id, float precio, String descripcion, String color, int anio, int cilindraje,
			Integer numeroPuertas, TipoCombustibleEnum tipocombustible, TipovehiculoEnum tipovehiculo,
			Persona persona, Ciudad ciudad, Marca marca, List<Favorito> favorito, List<Pregunta> pregunta,
			List<Fotovehiculo> fotoVehiculo, List<Caracteristica> caracteristica) {
		super();
		this.id = id;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.anio = anio;
		this.cilindraje = cilindraje;
		this.numeroPuertas = numeroPuertas;
		this.tipocombustible = tipocombustible;
		this.tipovehiculo = tipovehiculo;
		this.persona = persona;
		this.ciudad = ciudad;
		this.marca = marca;
		this.favorito = favorito;
		this.pregunta = pregunta;
		this.fotoVehiculo = fotoVehiculo;
		this.caracteristica = caracteristica;
	}
	
	public int getId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}   
	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}   
	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}   
	public int getCilindraje() {
		return this.cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}   
	public Integer getNumeropuertas() {
		return this.numeroPuertas;
	}

	public void setNumeropuertas(Integer numeropuertas) {
		this.numeroPuertas = numeropuertas;
	}
	
	

	

	/**
	 * Método hashcode de la entidad Vehiculo
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	/**
	 * Método equals de la entidad Vehiculo
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
   
}
