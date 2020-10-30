package co.edu.uniquindio.unimotor.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
import co.edu.uniquindio.unimotor.excepcion.PersonaInexistenteExcepcion;
import co.edu.uniquindio.unimotor.excepcion.VehiculoInexistenteExcepcion;

@Remote
public interface UnimotorEJBRemote {

	void registrarPersona (Persona persona) throws Exception;
	
	void modificarPersona (Persona persona) throws PersonaInexistenteExcepcion;
	
	void eliminarPersona (Persona persona) throws PersonaInexistenteExcepcion;
	

		
    Persona iniciarSesion (String email, String clave) throws Exception;
		
	void registrarVehiculo (Vehiculo vehiculo)throws Exception;
	
	List<Vehiculo> obtenerListaVehiculos();
	
	List <Persona> obtenerListaPersonas();
	
	void modificarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;
	
	void eliminarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;
	
	List <Vehiculo> obtenerListaVehiculosPorCiudad(Ciudad ciudad)throws Exception;
	
    void guardarVehiculoComoFavorito (Vehiculo vehiculo , Persona persona) throws Exception;
    
    void eliminarVehiculoComoFavorito (Vehiculo vehiculo , Persona persona) throws NullPointerException;
    
    void realizarPreguntaVehiculo (Pregunta pregunta) ;
    
    /*
     * Método que elimina una pregunta de un Vehiculo.
     * @param pregunta a eliminar.
     */
    void eliminarPreguntaVehiculo (Pregunta pregunta) throws NullPointerException;
	
	List <Caracteristica> obtenerListaCaracteristicas();
	
	List <Ciudad> obtenerListaCiudades();
	
	List <Modelo> obtenerListaModelos();
	
	void enviarConGMail (String destinatario,String asunto,String cuerpo) throws Exception;
	
	
	
	
}
