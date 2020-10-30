package co.edu.uniquindio.unimotor.excepcion;

public class VehiculoInexistenteExcepcion extends Exception {

	
	private static final long serialVersionUID = 1L;

	
	public VehiculoInexistenteExcepcion(String mensaje) {
		
		super(mensaje);
	}
}
