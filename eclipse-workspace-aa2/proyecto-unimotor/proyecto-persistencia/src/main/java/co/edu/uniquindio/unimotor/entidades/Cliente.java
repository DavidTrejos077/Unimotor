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
	

	
	
	

	public Cliente() {
		super();
	}

	/**
	 * @param favoritos
	 * @param preguntas
	 */
	public Cliente(int id, String nombre, String email, String clave, String direccion, Ciudad ciudad) {
		super(id,nombre,email,clave,direccion,ciudad);
	
		
	}
	
	/**
	 * @param favoritos
	 * @param preguntas
	 */
	public Cliente(int id, String nombre, String email, String clave, String direccion) {
		super(id,nombre,email,clave,direccion);
		
		
	}

	

	
	
	
   
}
