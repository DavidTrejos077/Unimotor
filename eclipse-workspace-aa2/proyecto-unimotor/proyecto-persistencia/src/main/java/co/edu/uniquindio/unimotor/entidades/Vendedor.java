package co.edu.uniquindio.unimotor.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase que implementa la entidad: Vendedor
 *
 */

@Entity
public class Vendedor extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	public Vendedor(int id, String nombre, String email, String clave, String direccion, Ciudad ciudad) {
		super(id,nombre,email,clave,direccion,ciudad);
	}

	public Vendedor(int id, String nombre, String email, String clave, String direccion) {
		super(id,nombre,email,clave,direccion);
	}
	
	public Vendedor () {
		super ();
	}
}
