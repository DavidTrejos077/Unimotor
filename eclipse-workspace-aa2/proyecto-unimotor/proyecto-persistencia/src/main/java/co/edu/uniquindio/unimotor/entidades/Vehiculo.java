package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Clase que implementa la entidad:Vehiculo
 *
 */
@Entity 
@NamedQueries ({ @NamedQuery(name = "TODOS_VEHICULOS", query = "select v from Vehiculo v"),
@NamedQuery(name = "TODOS_VEHICULOS_CIUDAD",query = "select v from Vehiculo v where v.ciudad = ?1"),
@NamedQuery(name = "BUSCAR_VEHICULOS", query = "select v from Vehiculo v where v.nombrePublicacion like :busqueda"),
                                                  //Solicitado      //Contexto                     //Condicion
@NamedQuery(name = "TODOS_VEHICULOS_ANIO",query = "select v from Vehiculo v where v.anio between 1960 and 2019"),
@NamedQuery(name = "VEHICULO_DESCRIPCION",query = "select v.descripcion from Vehiculo v where v.color = :color"),
@NamedQuery(name = "VEHICULO_COLOR",query = "select v.descripcion , v.cilindraje, v.precio from Vehiculo v where v.color = :color"),
@NamedQuery(name = "VEHICULO_PREGUNTAS",query = "select v, p.texto_de_la_pregunta from Vehiculo v left join v.preguntas p"),
@NamedQuery(name = "LISTA_VEHICULO_CARACTERISTICAS",query = " select c  from Vehiculo v join v.caracteristica c where v.id = :id"),

@NamedQuery(name="VEHICULO_FOTOS",query = "select v, f from Vehiculo v join v.fotoVehiculos f where v.modelo.marca.nombre = :marca and v.precio between :precioMin and :precioMax"),
@NamedQuery(name="VEHICULO_CARACTERISTICAS",query = "select  new co.edu.uniquindio.unimotor.dto.ConsultaVehiculoCaracteristicasDTO (v) from Vehiculo v join v.caracteristica c where c.nombre IN :lista"),
@NamedQuery (name="NUMERO_DE_VEHICULOS_CON_UNA_CARACTERISTICA_ESPECIFICA",query = "select COUNT(v) from Vehiculo v join v.caracteristica c where c.id = :id"),
@NamedQuery (name="NUMERO_DE_VEHICULOS_POR_CADA_MARCA",query ="select  new co.edu.uniquindio.unimotor.dto.ConsultaVehiculosPorCadaMarcaDTO (v.id,v.marca.nombre,count(v)) from Vehiculo v group by v.marca"),
@NamedQuery (name="LISTA_VEHICULOS_SIN_PREGUNTA",query = "select v from Vehiculo v where v.preguntas is empty"),
@NamedQuery (name="NUMERO_DE_VEHICULOS_POR_TIPO",query ="select  v.id,v.marca,count(v) from Vehiculo v group by v.tipovehiculo"),
@NamedQuery (name = "VALOR_PROMEDIO_DE_VEHICULOS",query = "select avg(v.precio) from Vehiculo v  where v.marca.id = :marca and v.carroNuevoUsado IN :tipoCarro or v.carroNuevoUsado IN :tipoCarro2 and v.kilometraje between :precioMin and :precioMax and v.ciudad.id = :ciudad"),
@NamedQuery (name = "LISTA_VEHICULOS_CON_TODAS_LAS_CARACTERISTICAS",query = "select v.id,v.persona, v.descripcion  from Vehiculo v join v.caracteristica c group by v having count (c) >4"),
@NamedQuery (name = "VEHICULO_NUEVO_MAS_COSTOSO",query = "select max (v.descripcion), v from Vehiculo v where v.carroNuevoUsado = :tipoCarro and  v.ciudad.id = :ciudad"),
@NamedQuery (name = "VEHICULO_MAS_COSTOSO_POR_CADA_MARCA",query = "select max (v.precio) from Vehiculo v group by v.marca"),
@NamedQuery (name = "KILOMETROS_RECORRIDOS_POR_MODELO",query = "select sum (v.kilometraje) from Vehiculo v group by v.modelo"),
@NamedQuery (name = "CANTIDAD_VEHICULOS_POR_MODELO",query = "select count (v) from Vehiculo v group by v.modelo order by v.marca.nombre asc"),
@NamedQuery (name = "CARACTERISTICA_COMUN_VEHICULOS",query = "select max (c.nombre) from Vehiculo v join v.caracteristica c"),
@NamedQuery (name = "BUSCAR_VEHICULO_POR_PLACA",query = "select v from Vehiculo v where v.placa = :placa"),
@NamedQuery(name = "LISTA_VEHICULOS_NOMBREPUBLICACION",query = "select v from Vehiculo v where v.nombrePublicacion like :nombrePublicacion"),
@NamedQuery(name= "VEHICULOS_POR_CIUDAD",query = " select v from Vehiculo v where v.ciudad = :ciudad"),
@NamedQuery(name = "LISTA_PREGUNTAS_POR_PLACA",query = "select p from Vehiculo v,IN (v.preguntas) p where v.placa = :placa"),
@NamedQuery(name = "LISTA_CARACTERISTICAS_VEHICULO",query = "select c from Vehiculo v join v.caracteristica c where v.id = :id"),
@NamedQuery(name = "LISTA_VEHICULOS_EMAIL",query = "select v from Vehiculo v where v.persona.id = :id"),
@NamedQuery (name="LISTA_VEHICULOS",query = "select v from Vehiculo v")

})
public class Vehiculo implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vehiculo", length=12)
	private int id;
	
	@NotBlank(message = "Nombre de publicacion no puede ser vacio")  
	@Size(max=200)
	@Column(name = "nombrePublicacion", length=200)
	private String nombrePublicacion;
	
	@Positive
	@Column(name = "precio", nullable=false)
	private long precio;
	
	
	@Column(name = "placa", nullable=false, unique=true)
	private String placa;
	
	@Positive 
	@Column (name = "kilometraje", nullable=false)
	private long kilometraje;
	
	@NotBlank(message = "La descripcion no puede ser vacia") 
	@Size(max=200)
	@Column(name = "descripcion", length=200, nullable=false)
	private String descripcion;
	
	@NotBlank(message = "El color no puede ser vacio")
	@Size(max=25)
	@Column(name = "color", length=25, nullable=false)
	private String color;
	
	@Positive 
	@Column(name = "anio", nullable=false)
	private int anio;
	
	 
	@Column(name = "cilindraje", nullable=false)
	private int cilindraje;
	
	@Max(5)
	@Min(1)
	@Positive
	@Column(name = "numeropuertas", nullable=false)
	private Integer numeroPuertas;
	
	private static final long serialVersionUID = 1L;
	
	@Enumerated (EnumType.STRING)
	private TipoCombustibleEnum tipocombustible;
	
	@Enumerated (EnumType.STRING)
	private Tranmision transmision;
	
	
	@Enumerated (EnumType.STRING)
	private OpcionNuevoUsado carroNuevoUsado;

	@Enumerated (EnumType.STRING)
	private TipovehiculoEnum tipovehiculo;
	
	@JsonbTransient
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
	@JoinColumn(name = "id_modelo", nullable=false)
	private Modelo modelo;
	
	@JsonbTransient
	@OneToMany(mappedBy = "vehiculo")
	@JoinColumn(nullable=true)
	private List<Favorito> favoritos;
	
	@JsonbTransient
	@OneToMany(mappedBy = "vehiculo")
	@JoinColumn(nullable=true)
	private List<Pregunta> preguntas;
	
	
	
	@ElementCollection
	@JoinColumn(nullable=false)
	private ArrayList<String> fotoVehiculos;
	
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

	
	
	public int getId() {
		return this.id;
	}


	






	










	public Vehiculo(int id, String nombrePublicacion, long precio,String placa, long kilometraje, String descripcion, String color, int anio, int cilindraje,
			@Max(5) @Min(1) Integer numeroPuertas, TipoCombustibleEnum tipocombustible, Tranmision transmision,
			OpcionNuevoUsado carroNuevoUsado, TipovehiculoEnum tipovehiculo, Persona persona, Ciudad ciudad,
			Marca marca, Modelo modelo, 
			List<Fotovehiculo> fotoVehiculo, List<Caracteristica> caracteristica, ArrayList <String>fotoVehiculos) {
		super();
		this.id = id;
		this.nombrePublicacion=nombrePublicacion;
		this.precio = precio;
		this.placa=placa;
		this.kilometraje = kilometraje;
		this.descripcion = descripcion;
		this.color = color;
		this.anio = anio;
		this.cilindraje = cilindraje;
		this.numeroPuertas = numeroPuertas;
		this.tipocombustible = tipocombustible;
		this.transmision = transmision;
		this.carroNuevoUsado = carroNuevoUsado;
		this.tipovehiculo = tipovehiculo;
		this.persona = persona;
		this.ciudad = ciudad;
		this.marca = marca;
		this.modelo = modelo;
		this.favoritos= new ArrayList<>();
		this.preguntas = new ArrayList<>();
		this.fotoVehiculos= new ArrayList<>();
		this.caracteristica = caracteristica;
	}










	public Integer getNumeroPuertas() {
		return numeroPuertas;
	}










	public void setNumeroPuertas(Integer numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}










	public TipoCombustibleEnum getTipocombustible() {
		return tipocombustible;
	}










	public void setTipocombustible(TipoCombustibleEnum tipocombustible) {
		this.tipocombustible = tipocombustible;
	}










	public Tranmision getTransmision() {
		return transmision;
	}










	public void setTransmision(Tranmision transmision) {
		this.transmision = transmision;
	}










	public TipovehiculoEnum getTipovehiculo() {
		return tipovehiculo;
	}










	public void setTipovehiculo(TipovehiculoEnum tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}










	public Persona getPersona() {
		return persona;
	}










	public void setPersona(Persona persona) {
		this.persona = persona;
	}










	public Ciudad getCiudad() {
		return ciudad;
	}










	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}










	










	










	










	









	public Modelo getModelo() {
		return modelo;
	}










	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}










	public List<Favorito> getFavoritos() {
		return favoritos;
	}










	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}










	public List<Pregunta> getPreguntas() {
		return preguntas;
	}










	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}










	










	public ArrayList<String> getFotoVehiculos() {
		return fotoVehiculos;
	}










	public void setFotoVehiculos(ArrayList<String> fotoVehiculos) {
		this.fotoVehiculos = fotoVehiculos;
	}










	public List<Caracteristica> getCaracteristica() {
		return caracteristica;
	}










	public void setCaracteristica(List<Caracteristica> caracteristica) {
		this.caracteristica = caracteristica;
	}










	public static long getSerialversionuid() {
		return serialVersionUID;
	}










	public String getNombrePublicacion() {
		return nombrePublicacion;
	}










	public void setNombrePublicacion(String nombrePublicacion) {
		this.nombrePublicacion = nombrePublicacion;
	}










	public void setId(int id) {
		this.id = id;
	}   
	public long getPrecio() {
		return this.precio;
	}

	public void setPrecio(long precio) {
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
	
	public Marca getMarca() {
		return marca;
	}


   public void setMarca(Marca marca) {
		this.marca = marca;
	}


	

	

	public long getKilometraje() {
	return kilometraje;
}










public void setKilometraje(long kilometraje) {
	this.kilometraje = kilometraje;
}










public String getPlaca() {
	return placa;
}










public void setPlaca(String placa) {
	this.placa = placa;
}










public OpcionNuevoUsado getCarroNuevoUsado() {
	return carroNuevoUsado;
}










public void setCarroNuevoUsado(OpcionNuevoUsado carroNuevoUsado) {
	this.carroNuevoUsado = carroNuevoUsado;
}



public String getImagenPrincipal () {
	
	if (!fotoVehiculos.isEmpty()) {
		return fotoVehiculos.get(0);
	}
	 return "defecto.jpg";
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






	/**
	 * Método ToString de la entidad Vehiculo
	 */



	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", precio=" + precio + ", placa=" + placa  + ", descripcion=" + descripcion + ", color=" + color
				+ ", anio=" + anio + ", cilindraje=" + cilindraje + ", numeroPuertas=" + numeroPuertas
				+ ", tipocombustible=" + tipocombustible + ", tipovehiculo=" + tipovehiculo + ", persona=" + persona
				+ ", ciudad=" + ciudad 
				+ ", modelo=" + modelo  + ", fotoVehiculo="
				+ fotoVehiculos + ", caracteristica=" + caracteristica + "]";
	}
	
	
	
	
	
   
}
