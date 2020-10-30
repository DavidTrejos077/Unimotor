package co.edu.uniquindio.unimotor.ejb;

import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.New;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.OpcionNuevoUsado;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
import co.edu.uniquindio.unimotor.entidades.Vendedor;
import co.edu.uniquindio.unimotor.excepcion.PersonaInexistenteExcepcion;
import co.edu.uniquindio.unimotor.excepcion.VehiculoInexistenteExcepcion;

/**
 * Session Bean implementation class UnimotorEJB
 */
@Stateless
@LocalBean
public class UnimotorEJB implements UnimotorEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UnimotorEJB() {

	}

	/**
	 * Método para registrar un cliente en la base de datos
	 */

	@Override
	public void registrarCliente (Cliente cliente) throws Exception {

		
		try {
			
	System.out.println("Entro a registrar cliente");
	if (buscarPersonaPorEmail(cliente.getEmail())) {

		throw new Exception("El email ya está registrado en la base de datos");
	}

	entityManager.persist(cliente);
} catch (Exception e) {
	
}
		

	}

	/**
	 * Método para modificar un cliente en la base de datos.
	 */

	@Override
	public void modificarCliente(Cliente cliente) throws PersonaInexistenteExcepcion {

		Cliente buscado = entityManager.find(Cliente.class, cliente.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("El cliente no esta registrado");

		}

		entityManager.merge(cliente);
	}

	/**
	 * Método para eliminar un cliente en la base de datos.
	 */

	public void eliminarCliente (Cliente cliente) throws PersonaInexistenteExcepcion {

		Persona buscado = entityManager.find(Cliente.class, cliente.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("La persona no esta registrada");

		}

		entityManager.remove(cliente);
	}
	
	/**
	 * Método para registrar un vendedor en la base de datos.
	 */

	@Override
	public void registrarVendedor (Vendedor vendedor) throws Exception {

		if (buscarPersonaPorEmail(vendedor.getEmail())) {

			throw new Exception("El email ya está registrado en la base de datos");
		}

		entityManager.persist(vendedor);

	}
	
	/**
	 * Método para modificar un vendedor en la base de datos.
	 */

	@Override
	public void modificarVendedor(Vendedor vendedor) throws PersonaInexistenteExcepcion {

		Vendedor buscado = entityManager.find(Vendedor.class, vendedor.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("El vendedor no esta registrado");

		}

		entityManager.merge(vendedor);
	}
	
	
	/**
	 * Método para eliminar un vendedor en la base de datos.
	 */

	public void eliminarVendedor (Vendedor vendedor) throws PersonaInexistenteExcepcion {

		Vendedor  buscado = entityManager.find(Vendedor.class, vendedor.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("la persona no esta registrada");

		}

		entityManager.remove(vendedor);
	}


	/**
	 * Método para buscar una persona existente en la base de datos de acuerdo a un
	 * email dado
	 */

	public boolean buscarPersonaPorEmail(String email) {

		TypedQuery<Persona> q = entityManager.createNamedQuery("BUSCAR_PERSONA_POR_CORREO", Persona.class);
		q.setParameter("email", email); // Email que llega por parametro le enviamos.
		List<Persona> l = q.getResultList();

		if (l.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Método para obtener la lista de Personas.
	 */
	@Override
	public List<Persona> obtenerListaPersonas() {

		TypedQuery<Persona> q = entityManager.createNamedQuery("LISTA_PERSONAS", Persona.class);
		List<Persona> l = q.getResultList();

		System.out.println(l);
		return l;

	}

	/**
	 * Método para iniciar sesion en la aplicación dado un email y una contraseña
	 */
	@Override
	public Persona iniciarSesion(String email, String clave) throws Exception {
		TypedQuery<Persona> q = entityManager.createNamedQuery("AUTENTICAR_PERSONA", Persona.class);

		q.setParameter("email", email);
		q.setParameter("clave", clave);

		List<Persona> l = q.getResultList();

		if (l.isEmpty()) {
			throw new Exception("Los datos de autenticacion son incorrectos");
		}

		return l.get(0);
	}

	/**
	 * Método para registrar un vehiculo de acuerdo a una placa dada
	 * 
	 */
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws Exception {
		if (buscarVehiculoPorPlaca(vehiculo.getPlaca())) {

			throw new Exception("La placa ya está registrada");
		}

		entityManager.persist(vehiculo);

	}

	/**
	 * Método para buscar placa de Vehiculo y saber si ya está registrada.
	 */

	public boolean buscarVehiculoPorPlaca(String placa) {

		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("BUSCAR_VEHICULO_POR_PLACA", Vehiculo.class);
		q.setParameter("placa", placa); // placa que llega por parametro le enviamos.
		List<Vehiculo> l = q.getResultList();

		if (l.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Método para obtener la lista de Vehiculos.
	 */

	@Override
	public List<Vehiculo> obtenerListaVehiculos() {

		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("LISTA_VEHICULOS", Vehiculo.class);
		List<Vehiculo> l = q.getResultList();

		System.out.println(l);
		return l;

	}

	/**
	 * Método para modificar un vehiculo.
	 */
	@Override
	public void modificarVehiculo(Vehiculo vehiculo) throws VehiculoInexistenteExcepcion {

		Vehiculo buscado = entityManager.find(Vehiculo.class, vehiculo.getId());

		if (buscado == null) {

			throw new VehiculoInexistenteExcepcion("El vehiculo no esta registrado");
		}

		entityManager.merge(vehiculo);
	}

	/**
	 * Método para eliminar un vehiculo.
	 */

	public void eliminarVehiculo(Vehiculo vehiculo) throws VehiculoInexistenteExcepcion {

		Vehiculo buscado = entityManager.find(Vehiculo.class, vehiculo.getId());

		if (buscado == null) {

			throw new VehiculoInexistenteExcepcion("El vehiculo no esta registrado");
		}

		entityManager.remove(vehiculo);
	}

	/**
	 * Método para obtener la lista de Vehiculos de acuerdo a una ciudad.
	 */

	@Override
	public List<Vehiculo> obtenerListaVehiculosPorCiudad(Ciudad ciudad) throws Exception {

		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("VEHICULOS_POR_CIUDAD", Vehiculo.class);
		q.setParameter("ciudad", ciudad);

		List<Vehiculo> l = q.getResultList();

		if (l.isEmpty()) {

			throw new Exception("No encontramos vehiculos en la ciudad: " + ciudad);
		}

		return l;
	}

	/**
	 * Método para obtener la lista de caracteristicas
	 */

	@Override
	public List<Caracteristica> obtenerListaCaracteristicas() {

		TypedQuery<Caracteristica> q = entityManager.createNamedQuery("LISTA_CARACTERISTICAS", Caracteristica.class);
		List<Caracteristica> l = q.getResultList();

		System.out.println(l);
		return l;

	}

	/**
	 * Método para obtener la lista de ciudades.
	 */

	@Override
	public List<Ciudad> obtenerListaCiudades() {
		TypedQuery<Ciudad> q = entityManager.createNamedQuery("LISTA_CIUDADES", Ciudad.class);
		List<Ciudad> l = q.getResultList();

		System.out.println(l);
		return l;

	}

	/**
	 * Método para obtener la lista de modelos.
	 */
	@Override
	public List<Modelo> obtenerListaModelos() {

		TypedQuery<Modelo> q = entityManager.createNamedQuery("LISTA_MODELOS", Modelo.class);
		List<Modelo> l = q.getResultList();

		System.out.println(l);
		return l;
	}

	

	

		

	/**
	 * Método para guardar un Vehiculo como favorito.
	 */
	@Override
	public void guardarVehiculoComoFavorito(Vehiculo vehiculo, Cliente cliente) throws Exception {

		TypedQuery<Favorito> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA", Favorito.class);
		System.out.println(cliente.getNombre());
		q.setParameter("email", cliente.getEmail());

		List<Favorito> l = q.getResultList();

		Favorito f = new Favorito(cliente, vehiculo);

		for (Favorito favorito : l) {
			
			if (favorito.getVehiculo().getPlaca() == (vehiculo.getPlaca())) {
				throw new Exception("Este Vehiculo ya hace parte de tu lista de favoritos");
			}
		}

		l.add(f);

		cliente.setFavoritos(l);

		entityManager.merge(cliente);
	}

	/**
	 * Método para eliminar un Vehiculo como favorito.
	 */
	@Override
	public void eliminarVehiculoComoFavorito(Vehiculo vehiculo, Cliente cliente) throws NullPointerException {

		TypedQuery<Favorito> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA", Favorito.class);
		q.setParameter("email", cliente.getEmail());

		List<Favorito> l = q.getResultList();

		for (Favorito favorito : l) {

			if (favorito.getVehiculo().getPlaca().equals(vehiculo.getPlaca())) {
				l.remove(favorito);
				break;
			}
		}

		cliente.setFavoritos(l);

		entityManager.merge(cliente);

	}

	@Override
	public void realizarPreguntaVehiculo(Pregunta pregunta) {
		Vehiculo v = entityManager.find(Vehiculo.class, pregunta.getVehiculo().getId());
		TypedQuery<Pregunta> q = entityManager.createNamedQuery("LISTA_PREGUNTAS_POR_PLACA", Pregunta.class);

		q.setParameter("placa", pregunta.getVehiculo().getPlaca());
		List<Pregunta> l = q.getResultList();
		l.add(pregunta);

		pregunta.getVehiculo().setPreguntas(l);

		entityManager.merge(pregunta.getVehiculo());
	}

	@Override
	public void eliminarPreguntaVehiculo(Pregunta pregunta) throws NullPointerException {
		
		TypedQuery<Pregunta> q = entityManager.createNamedQuery("LISTA_PREGUNTAS_POR_PLACA", Pregunta.class);
		q.setParameter("placa", pregunta.getVehiculo().getPlaca());
		
		List<Pregunta> l =q.getResultList();
		for (Pregunta pregunta1 : l) {

			if (pregunta1.getId()== pregunta.getId()) {
				
				l.remove(pregunta1);
				break;
			}
		}

	
        pregunta.getVehiculo().setPreguntas(l);
		entityManager.merge(pregunta.getVehiculo());

	 }
    /**
     * Método para obtener la lista de preguntas de cualquier vehiculo.
     */
	@Override
	public List<Pregunta> obtenerListaPreguntas() {
		
		TypedQuery<Pregunta> q = entityManager.createNamedQuery("LISTA_PREGUNTAS_POR_CADA_VEHICULO", Pregunta.class);
		List<Pregunta> l = q.getResultList();

		
		
		for (Pregunta p : l) {
			System.out.println(p);
		}
		
		
		return l;

	
	}

	@Override
	public List<Vehiculo> buscarVehiculosPorFiltro(OpcionNuevoUsado carroNuevoUsado, int idMarca, long precioMin,long precioMax, int anioInicio, int anioFin) throws Exception {
		
	String consulta = "select v from Vehiculo v ";
	String filtros = "";
	
	
	
	if(carroNuevoUsado != null) {
		
		if (filtros.equals("")) {
			filtros = "where v.carroNuevoUsado = :tipo ";
		}else {
			filtros+= "and v.carroNuevoUsado = :tipo ";
		}
	}
	
   if(idMarca == 0) {
		
		if (filtros.equals("")) {
			filtros = "where v.marca.id = :id ";
		}else {
			filtros+= "and v.marca.id = :id ";
		}
	}
	
	
   if(precioMin > 0 && precioMax >0) {
		
		if (filtros.equals("")) {
			filtros = "where v.precio between :precioMin and :precioMax ";
		}else {
			filtros+= "and v.precio between :precioMin and :precioMax ";
		}
	}
   
   if(anioInicio > 0 && anioFin >0) {
		
		if (filtros.equals("")) {
			filtros = "where v.anio between :anioInicio and :anioFin ";
		}else {
			filtros+= "and v.anio between :anioInicio and :anioFin ";
		}
	}
  
   consulta +=  filtros;
   
	TypedQuery<Vehiculo> q = entityManager.createNamedQuery(consulta, Vehiculo.class);
	List<Vehiculo> l = q.getResultList();
   
	return l;
	
	
	
	}

	@Override
	public void responderPregunta(Pregunta pregunta, String respuesta) throws Exception {
		
		Pregunta preguntaNueva = new Pregunta(respuesta, pregunta.getPersona(), pregunta.getVehiculo());
		
		pregunta.setRespuesta(preguntaNueva);
		
		entityManager.merge(pregunta);
		
	}

	
	

	
	
		
	}

	
