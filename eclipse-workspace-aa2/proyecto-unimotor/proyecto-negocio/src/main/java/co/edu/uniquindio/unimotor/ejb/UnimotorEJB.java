package co.edu.uniquindio.unimotor.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	 * Método para obtener la marca de acuerdo a un id.
	 */

	@Override
	public Marca obtenerMarca (Integer id) throws Exception {
		Marca m= entityManager.find(Marca.class, id);
		if(m!=null) {
			return m;
		}else {
			throw new Exception("La marca no existe");
		}
	}

	/**
	 * Método para buscar el nombre de la marca.
	 */

	public boolean buscarNombreMarca (String nombreMarca) {

		TypedQuery<Marca> q= entityManager.createNamedQuery("MARCA_POR_NOMBRE", Marca.class);
		q.setParameter("nombre", nombreMarca);

		List <Marca> l =q.getResultList();

		if (l.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Método para buscar el vehiculo dado una placa.
	 */

	public boolean buscarVehiculo (String placa) {

		TypedQuery<Vehiculo> q= entityManager.createNamedQuery("BUSCAR_VEHICULO_POR_PLACA", Vehiculo.class);
		q.setParameter("placa", placa);

		List <Vehiculo> l =q.getResultList();

		if (l.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Método para guardar la marca.
	 */

	@Override
	public void guardarMarca (Marca marca)throws Exception{
		if (buscarNombreMarca (marca.getNombre())) {
			throw new Exception("El nombre de la marca ya está registrado");
		}
		entityManager.persist(marca);
	}
	

	/**
	 * Método para guardar un vehiculo.
	 */

	@Override
	public void guardarVehiculo (Vehiculo vehiculo)throws Exception{
		if (buscarVehiculo (vehiculo.getPlaca())) {
			throw new Exception("El vehiculo  ya está registrado");
		}
		entityManager.persist(vehiculo);
	}
	

	/**
	 * Método para eliminar la marca de acuerdo a un id.
	 */
	@Override
	public void eliminarMarca (Integer id)throws Exception {
		Marca marca = entityManager.find(Marca.class, id);
		if (marca!=null) {
			entityManager.remove(marca);
		}else {
			throw new Exception("La marca no existe");
		}
	}
	
	/**
	 * Método para eliminar vehiculo de acuerdo a un id.
	 */
	@Override
	public void eliminarVehiculo (Integer id)throws Exception {
		Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
		if (vehiculo!=null) {
			entityManager.remove(vehiculo);
		}else {
			throw new Exception("El Vehiculo no existe");
		}
	}
	
	/**
	 * Método para eliminar Persona de acuerdo a un id.
	 */
	@Override
	public void eliminarPersona (Integer id)throws Exception {
		Persona persona = entityManager.find(Persona.class, id);
		if (persona!=null) {
			entityManager.remove(persona);
		}else {
			throw new Exception("La persona no existe");
		}
	}
	
	/**
	 * Método para actualizar un vehiculo.
	 */
	
	@Override	
	public void actualizarVehiculo (Vehiculo vehiculo )throws Exception {
		if (vehiculo!=null) {
			entityManager.merge(vehiculo);
		}else {
			throw new Exception("El vehiculo es null");
		}
	}
	
	/**
	 * Método para actualizar una persona.
	 */
	
	@Override	
	public void actualizarPersona (Persona persona )throws Exception {
		if (persona!=null) {
			entityManager.merge(persona);
		}else {
			throw new Exception("La persona es null");
		}
	}
	
	@Override	
	public void actualizarMarca (Marca marca )throws Exception {
		if (marca!=null) {
			entityManager.merge(marca);
		}else {
			throw new Exception("La marca es null");
		}
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

	/**
	 * Este método les funciona?Si profe
	 * @param email
	 * @return
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
		System.out.println("registrarVehiculo");
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
		q.setParameter("placa", placa); 
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
		System.out.println("obtenerListaCiudades");
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
	 * Método para obtener la lista de modelos por marca.
	 */
	@Override
	public List<Modelo> obtenerListaModelosPorMarca(Marca marca) {

		TypedQuery<Modelo> q = entityManager.createNamedQuery("LISTA_MODELOS_MARCA", Modelo.class);
		q.setParameter("marca", marca);
		List<Modelo> l = q.getResultList();

		System.out.println(l);
		return l;
	}




	/**
	 * Método para guardar un Vehiculo como favorito.
	 */
	@Override
	public void guardarVehiculoComoFavorito(Vehiculo vehiculo, Persona persona) throws Exception {

		TypedQuery<Favorito> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA", Favorito.class);
		System.out.println(persona.getNombre());
		q.setParameter("email", persona.getEmail());

		List<Favorito> l = q.getResultList();

		Favorito f = new Favorito(persona, vehiculo);

		for (Favorito favorito : l) {

			if (favorito.getVehiculo().getPlaca() == (vehiculo.getPlaca())) {
				throw new Exception("Este Vehiculo ya hace parte de tu lista de favoritos");
			}
		}

		l.add(f);

		persona.setFavoritos(l);

		entityManager.merge(persona);
	}

	/**
	 * Método para eliminar un Vehiculo como favorito.
	 */
	@Override
	public void eliminarVehiculoComoFavorito(Vehiculo vehiculo, Persona persona) throws NullPointerException {

		TypedQuery<Favorito> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA", Favorito.class);
		q.setParameter("email", persona.getEmail());

		List<Favorito> l = q.getResultList();

		for (Favorito favorito : l) {

			if (favorito.getVehiculo().getPlaca().equals(vehiculo.getPlaca())) {
				l.remove(favorito);
				break;
			}
		}

		persona.setFavoritos(l);

		entityManager.merge(persona);

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
	public Vehiculo obtenerVehiculo (Integer id)throws Exception {

		Vehiculo v= entityManager.find(Vehiculo.class, id);

		if (v== null) {
			throw new Exception("El id del vehiculo no existe");
		}
		return v;
	}
	
	@Override
	public Persona obtenerPersona (Integer id)throws Exception {

		Persona p= entityManager.find(Persona.class, id);

		if (p== null) {
			throw new Exception("El id de la persona no existe");
		}
		return p;
	}

	@Override
	public List <Pregunta> obtenerPreguntasVehiculo (Integer codigoV){
		TypedQuery<Pregunta>q =entityManager.createNamedQuery("LISTA_PREGUNTAS", Pregunta.class);
		q.setParameter("id", codigoV);
		return q.getResultList();
	}

	@Override
	public List <Caracteristica> obtenerCaracteristicasVehiculo (Integer codigoV){
		TypedQuery<Caracteristica>q =entityManager.createNamedQuery("LISTA_CARACTERISTICAS_VEHICULO", Caracteristica.class);
		q.setParameter("id", codigoV);
		return q.getResultList();
	}

	@Override
	public Pregunta hacerPregunta (Persona persona, Vehiculo vehiculo, String texto_de_la_pregunta)throws Exception {
		try {


			Pregunta pregunta = null;

			if(persona!=null&&vehiculo!=null) {

				pregunta = new Pregunta(texto_de_la_pregunta, persona, vehiculo);
				entityManager.persist(pregunta);
			}else {
				throw new Exception("Es necesario definir una persona y un vehiculo para registrar la pregunta");
			}

			return pregunta;
		}catch (Exception e) {
			throw new Exception("Hubo un error al momento de registrar la pregunta");
		}
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

	/**
	 * Ojo que tiene  dos métodos que hacen lo mismo
	 */
	@Override
	public Cliente obtenerPorEmail(String email) throws Exception {

		TypedQuery<Cliente> q = entityManager.createNamedQuery("BUSCAR_PERSONA_POR_CORREO", Cliente.class);
		q.setParameter("email", email);

		List<Cliente> l = q.getResultList();

		if (l.isEmpty()) {
			throw new Exception("Los datos de autenticacion son incorrectos");
		}

		System.out.println(l);

		return l.get(0);




	}

	public List<Marca> obtenerListaMarcas() {
		TypedQuery<Marca> q = entityManager.createNamedQuery("LISTA_MARCA", Marca.class);
		List<Marca> l = q.getResultList();

		System.out.println(l);
		return l;
	}



	/**
	 * Este método les funciona?Si profe
	 * @param email
	 * @return
	 */

	public Persona buscarPersona(String email) throws Exception{

		TypedQuery<Persona> q = entityManager.createNamedQuery("BUSCAR_PERSONA_POR_CORREO", Persona.class);
		q.setParameter("email", email); // Email que llega por parametro le enviamos.
		List<Persona> l = q.getResultList();

		if (l.isEmpty()) {
			throw new Exception("Los datos de autenticacion son incorrectos");
		}

		return l.get(0);
	}

	/**
	 * Método para modificar un cliente en la base de datos.
	 */

	@Override
	public void modificarPersona(Persona persona) throws PersonaInexistenteExcepcion {

		System.out.println("Actualizando Persona");
		Persona buscado = entityManager.find(Persona.class, persona.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("El cliente no esta registrado");

		}
		entityManager.merge(persona);
	}


	@Override
	public void registrarPersona (Persona persona) throws Exception {

		try {

			System.out.println("Entro a registrar cliente");
			if (buscarPersonaPorEmail(persona.getEmail())) {

				throw new Exception("El email ya está registrado en la base de datos");
			}

			entityManager.persist(persona);
		} catch (Exception e) {

		}


	}

	@Override
	public List<Vehiculo> buscarVehiculos(String busqueda) {

		TypedQuery<Vehiculo> q= entityManager.createNamedQuery("BUSCAR_VEHICULOS", Vehiculo.class);
		q.setParameter("busqueda", "%" +busqueda+ "%");
		return q.getResultList();
	}

	@Override
	public Caracteristica obtenerCaracteristica(Integer id) {
		return entityManager.find(Caracteristica.class,id);
	}

	public List<Vehiculo> buscarVehiculosPorCliente(int id) {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("LISTA_VEHICULOS_EMAIL", Vehiculo.class);
		q.setParameter("id", id);
		List<Vehiculo> l = q.getResultList();

		System.out.println(l);
		return l;
	}

}


