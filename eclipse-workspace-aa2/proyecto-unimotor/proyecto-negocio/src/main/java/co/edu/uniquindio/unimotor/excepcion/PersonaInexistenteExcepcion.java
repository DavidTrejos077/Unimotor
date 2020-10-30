package co.edu.uniquindio.unimotor.excepcion;

public class PersonaInexistenteExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PersonaInexistenteExcepcion (String mensaje) {
		
		super(mensaje);
	}
}
