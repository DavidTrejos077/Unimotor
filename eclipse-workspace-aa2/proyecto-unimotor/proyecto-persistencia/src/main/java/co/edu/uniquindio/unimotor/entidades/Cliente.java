package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que implementa la entidad: Cliente
 *
 */


@Entity
@NamedQueries ({
	@NamedQuery(name = "LISTA_FAVORITOS_PERSONA_JOIN",query = "select f.vehiculo from Cliente c join c.favoritos f where c.email = :email"),
	@NamedQuery(name = "LISTA_FAVORITOS_PERSONA",query = "select f from Cliente c,IN (c.favoritos) f where c.email = :email"),
})
public class Cliente extends Persona implements Serializable  {

	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente")
	private List<Favorito> favoritos;
	
	@OneToMany(mappedBy = "persona")
	private List<Pregunta> preguntas;
	
	@OneToMany(mappedBy = "persona")
	private List<Vehiculo> vehiculos;

	public Cliente() {
		super();
	}

	/**
	 * @param favoritos
	 * @param preguntas
	 */
	public Cliente(int id, String nombre, String email, String clave, String direccion, Ciudad ciudad) {
		super(id,nombre,email,clave,direccion,ciudad);
		this.favoritos = new ArrayList<>();
		this.preguntas = new ArrayList<>();
	}
	
	/**
	 * @param favoritos
	 * @param preguntas
	 */
	public Cliente(int id, String nombre, String email, String clave, String direccion) {
		super(id,nombre,email,clave,direccion);
		this.favoritos = new ArrayList<>();
		this.preguntas = new ArrayList<>();
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
	
	
   
}
