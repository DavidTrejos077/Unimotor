package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Clase que implementa la entidad:Pregunta
 *
 */
@Entity

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
	@JoinColumn(name = "id_pregunta", nullable=true)
	private Pregunta pregunta;
	
	
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
	
	public Pregunta(int id, String texto_de_la_pregunta, Date fecha) {
		super();
		this.id = id;
		this.texto_de_la_pregunta = texto_de_la_pregunta;
		this.fecha = fecha;
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
