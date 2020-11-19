package co.edu.uniquindio.unimotor.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.OpcionNuevoUsado;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
import co.edu.uniquindio.unimotor.entidades.Vendedor;
import co.edu.uniquindio.unimotor.excepcion.PersonaInexistenteExcepcion;
import co.edu.uniquindio.unimotor.excepcion.VehiculoInexistenteExcepcion;

@Remote
public interface UnimotorEJBRemote {

	void registrarCliente (Cliente cliente) throws Exception;
	
	void modificarCliente (Cliente cliente) throws PersonaInexistenteExcepcion;
	  
	
	
	void eliminarCliente (Cliente cliente) throws PersonaInexistenteExcepcion;
	
   void registrarVendedor (Vendedor vendedor) throws Exception;
	
	void modificarVendedor (Vendedor vendedor) throws PersonaInexistenteExcepcion;
	
	void eliminarVendedor (Vendedor vendedor) throws PersonaInexistenteExcepcion;
		
    Persona iniciarSesion (String email, String clave) throws Exception;
		
	void registrarVehiculo (Vehiculo vehiculo)throws Exception;
	
	List<Vehiculo> obtenerListaVehiculos();
	
	List <Pregunta> obtenerListaPreguntas();
	
	List <Persona> obtenerListaPersonas();
	
	Cliente obtenerPorEmail (String email) throws Exception;
	
	void modificarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;
	
	void eliminarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;
	
	List <Vehiculo> obtenerListaVehiculosPorCiudad(Ciudad ciudad)throws Exception;
	
	void responderPregunta (Pregunta pregunta, String respuesta)throws Exception;
	
    void guardarVehiculoComoFavorito (Vehiculo vehiculo , Cliente cliente) throws Exception;
    
    void eliminarVehiculoComoFavorito (Vehiculo vehiculo , Cliente cliente) throws NullPointerException;
    
    void realizarPreguntaVehiculo (Pregunta pregunta) ;
    
    /*
     * Método que elimina una pregunta de un Vehiculo.
     * @param pregunta a eliminar.
     */
    void eliminarPreguntaVehiculo (Pregunta pregunta) throws NullPointerException;
	
	List <Caracteristica> obtenerListaCaracteristicas();
	
	List <Ciudad> obtenerListaCiudades();
	
	List <Modelo> obtenerListaModelos();
	
	
	
	List <Vehiculo> buscarVehiculosPorFiltro (OpcionNuevoUsado carroNuevoUsado, int idMarca, long precioMin,long precioMax,int anioInicio,int anioFin) throws Exception;

	/**
	 * Método para obtener la lista de modelos por marca.
	 */
	List<Modelo> obtenerListaModelosPorMarca(Marca marca);

	public Persona buscarPersona(String email) throws Exception;
	
	public void modificarPersona(Persona persona) throws PersonaInexistenteExcepcion;
	
	public void registrarPersona (Persona persona) throws Exception;
		
	List <Vehiculo> buscarVehiculos (String busqueda);
	
	
	
}
