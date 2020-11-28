package co.edu.uniquindio.unimotor.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
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

	/**
	 * Método para registrar cliente.
	 */
	void registrarCliente (Cliente cliente) throws Exception;
	/**
	 * Método para modificar cliente.
	 */

	void modificarCliente (Cliente cliente) throws PersonaInexistenteExcepcion;


	/**
	 * Método para eliminar cliente.
	 */
	void eliminarCliente (Cliente cliente) throws PersonaInexistenteExcepcion;

	/**
	 * Método para registrar vendedor
	 */

	void registrarVendedor (Vendedor vendedor) throws Exception;

	/**
	 * Método para modificar vendedor.
	 */

	void modificarVendedor (Vendedor vendedor) throws PersonaInexistenteExcepcion;

	/**
	 * Método para eliminar vendedor.
	 */

	void eliminarVendedor (Vendedor vendedor) throws PersonaInexistenteExcepcion;

	/**
	 * Método para iniciar sesión.
	 */

	Persona iniciarSesion (String email, String clave) throws Exception;

	/**
	 * Método para registrar vehiculo.
	 */

	void registrarVehiculo (Vehiculo vehiculo)throws Exception;

	/**
	 * Metodo que obtiene la lista de vehiculos.
	 */

	List<Vehiculo> obtenerListaVehiculos();

	/**
	 * Metodo que obtiene la lista de preguntas.
	 */

	List <Pregunta> obtenerListaPreguntas();

	/**
	 * Metodo que obtiene la lista de personas.
	 */

	List <Persona> obtenerListaPersonas();

	/**
	 * Metodo para obtener un cliente dado un email.
	 */

	Cliente obtenerPorEmail (String email) throws Exception;

	/**
	 * Método para modificar vehiculo
	 */


	void modificarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;

	/**
	 * Método para eliminar vehiculo
	 */

	void eliminarVehiculo (Vehiculo vehiculo)throws VehiculoInexistenteExcepcion;

	/**
	 * Metodo que obtiene la lista de vehiculo dada una ciudad.
	 */

	List <Vehiculo> obtenerListaVehiculosPorCiudad(Ciudad ciudad)throws Exception;

	/**
	 * Método para eliminar vehiculo.
	 */

	void responderPregunta (Pregunta pregunta, String respuesta)throws Exception;

	/**
	 * Método para guardar vehiculo como favorito
	 */

	void guardarVehiculoComoFavorito (Vehiculo vehiculo , Persona persona) throws Exception;

	/**
	 * Método para eliminar vehiculo como favorito
	 */

	void eliminarVehiculoComoFavorito (Vehiculo vehiculo , Persona persona) throws NullPointerException;

	/**
	 * Método para realizar pregunta vehiculo 
	 */

	void realizarPreguntaVehiculo (Pregunta pregunta) ;

	/*
	 * Método que elimina una pregunta de un Vehiculo.
	 * @param pregunta a eliminar.
	 */
	void eliminarPreguntaVehiculo (Pregunta pregunta) throws NullPointerException;

	/**
	 * Metodo que obtiene la lista de caracteristicas dada una caracteristica.
	 */

	List <Caracteristica> obtenerListaCaracteristicas();

	/**
	 * Metodo que obtiene la lista de una ciudad dada ciudades.
	 */

	List <Ciudad> obtenerListaCiudades();

	/**
	 * Metodo que obtiene la lista de modelo dada los modelos. 
	 */

	List <Modelo> obtenerListaModelos();


	/**
	 * Metodo que obtiene la lista de vehiculo dado un vehiculo nuevo y usado, marca, precio y año.
	 */

	List <Vehiculo> buscarVehiculosPorFiltro (OpcionNuevoUsado carroNuevoUsado, int idMarca, long precioMin,long precioMax,int anioInicio,int anioFin) throws Exception;

	/**
	 * Método para obtener la lista de modelos por marca.
	 */
	List<Modelo> obtenerListaModelosPorMarca(Marca marca);

	/**
	 * Método para buscar una persona dado un email.
	 */
	public Persona buscarPersona(String email) throws Exception;

	/**
	 * Método para modificar una persona. 
	 */

	public void modificarPersona(Persona persona) throws PersonaInexistenteExcepcion;

	/**
	 * Método para registrar una persona.
	 */

	public void registrarPersona (Persona persona) throws Exception;

	/**
	 * Método para obtener la lista de vehiculos dado una busqueda.
	 */

	List <Vehiculo> buscarVehiculos (String busqueda);

	/**
	 * Método para obtener un vehiculo dado un id.
	 */

	Vehiculo obtenerVehiculo(Integer id);

	/**
	 * Método para obtener la lista de pregunta dado un codigoV.
	 */

	List<Pregunta> obtenerPreguntasVehiculo(Integer codigoV);

	/**
	 * Método para obtener la lista de caracteristicas dado un codigo.
	 */

	List<Caracteristica> obtenerCaracteristicasVehiculo(Integer codigoV);

	/**
	 * Método para obtener una pregunta dado una persona, vehiculo, textopregunta. 
	 */

	Pregunta hacerPregunta(Persona persona, Vehiculo vehiculo, String textoPregunta)throws Exception;

	/**
	 * Método para obtener una caracteristica dado un id.
	 */

	Caracteristica obtenerCaracteristica (Integer id);

}
