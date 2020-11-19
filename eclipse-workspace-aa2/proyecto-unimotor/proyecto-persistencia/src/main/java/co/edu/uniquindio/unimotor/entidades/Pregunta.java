package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que implementa la entidad:Pregunta
 *
 */
@Entity

@NamedQueries ({
	                                                              //Seleccioneme todas las personas que hicieron una pregunta sobre este carro especifico
	@NamedQuery (name = "LISTA_PERSONAS_PREGUNTA_VEHICULO",query = "select distinct p.persona from Pregunta p where p.vehiculo.id = :id"),
	@NamedQuery(name= "LISTA_PREGUNTAS_POR_CADA_VEHICULO",query = "select p from Pregunta p group by p.vehiculo")
})

public class Pregunta implements Serializable {

	  
	//	What´s the difference between @GeneratedValue(strategy = GenerationType.IDENTITY) & @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Textodelapregunta", nullable=false)
	private String texto_de_la_pregunta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable=true)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_persona", nullable=false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable=false)
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name = "id_pregunta", nullable=true)	private Pregunta respuesta;
	
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * Constructor por defecto de la entidad pregunta
	 *
	 */
	public Pregunta() {
		super();
	}  
	
	/**
	 * Constructor de la entidad pregunta con todos sus atributos
	 *
	 */
	
	public Pregunta( String texto_de_la_pregunta, Persona persona, Vehiculo vehiculo) {
		super();	
		this.texto_de_la_pregunta = texto_de_la_pregunta;		this.fecha = new Date();
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

	public Pregunta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Pregunta respuesta) {
		this.respuesta = respuesta;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTexto_de_la_pregunta() {
		return this.texto_de_la_pregunta;
	}

	public void setTexto_de_la_pregunta(String texto_de_la_pregunta) {
		this.texto_de_la_pregunta = texto_de_la_pregunta;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Método hash code de la entidad Pregunta
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Método equals de la entidad Pregunta
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pregunta other = (Pregunta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
   
}
