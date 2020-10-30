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
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
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
	 * Método para guardar una persona en la base de datos
	 */

	@Override
	public void registrarPersona(Persona persona) throws Exception {

		if (buscarPersonaPorEmail(persona.getEmail())) {

			throw new Exception("El email ya está registrado en la base de datos");
		}

		entityManager.persist(persona);

	}

	/**
	 * Método para modificar una persona en la base de datos.
	 */

	@Override
	public void modificarPersona(Persona persona) throws PersonaInexistenteExcepcion {

		Persona buscado = entityManager.find(Persona.class, persona.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("La persona no esta registrada");

		}

		entityManager.merge(persona);
	}

	/**
	 * Método para eliminar una persona en la base de datos.
	 */

	public void eliminarPersona(Persona persona) throws PersonaInexistenteExcepcion {

		Persona buscado = entityManager.find(Persona.class, persona.getId());

		if (buscado == null) {

			throw new PersonaInexistenteExcepcion("La persona no esta registrada");

		}

		entityManager.remove(persona);
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
	 * Método para buscar una persona existente en la base de datos de acuerdo a un
	 * email dado
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
	 * Método para enviar un correo mediante GMAIL
	 */

	public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "davidtrejoscortes"; // Para la dirección nomcuenta@gmail.com
		String clave = "marcaselausadi";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "miClaveDeGMail"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // Se podrían añadir varios de la misma
			// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");

			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

	public void main(String[] args) {
		String destinatario = "juanstc@gmail.com"; // A quien le quieres escribir.
		String asunto = "Correo de prueba enviado desde Java";
		String cuerpo = "Esta es una prueba de correo...";

		enviarConGMail(destinatario, asunto, cuerpo);
	}

	/**
	 * Método para guardar un Vehiculo como favorito.
	 */
	@Override
	public void guardarVehiculoComoFavorito(Vehiculo vehiculo, Persona persona) throws Exception {

		TypedQuery<Favorito> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA", Favorito.class);
		q.setParameter("email", persona.getEmail());

		List<Favorito> l = q.getResultList();

		Favorito f = new Favorito(persona, vehiculo);

		for (Favorito favorito : l) {
			System.out.println(favorito.getVehiculo().getId());
			System.out.println(vehiculo.getId());
			if (favorito.getVehiculo().getPlaca() == (vehiculo.getPlaca())) {
				throw new Exception("Este Vehiculo ya hace parte de tu lista de favoritos");
			}
		}

		l.add(f);

		persona.setFavorito(l);

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

		persona.setFavorito(l);

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
		
	}

	
