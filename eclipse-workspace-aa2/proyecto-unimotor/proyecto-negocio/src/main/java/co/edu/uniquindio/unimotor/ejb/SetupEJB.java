package co.edu.uniquindio.unimotor.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Persona;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public SetupEJB() {

	}

	//LISTO, YA QUEDÓ TODO BIEN, HABÍAN ALGUNOS ERRORES TANTO EN EL QUERY COMO EN LAS ENTIDADES. PERO LISTO Listo profe gracias otra consulta nosotros estamos utilizando enum para tipoVhiculo y tipoCombustible pero no sabemos si se es este cumpliendo bien la funcion porque en ultimas hicimos la relacion con entidades aparte y por eso esta duplicado usted que nos aconseja que borremos los enum y lo dejemos como esta la relacion o que sera lo mejor?
	//ES MEJOR QUE MANEJEN TANTO EL COMBUSTIBLE COMO EL TIPO DE VEHÍCULO COMO ENUM, MUCHO MÁS FÁCIL Y MANEJABLE. LISTO GRACIAS PROFE!
	
	@PostConstruct
	public void config () {

		TypedQuery<Long> q = entityManager.createNamedQuery("TODAS_PERSONAS", Long.class); // el query debe devolver un valor numérico

		if (q.getSingleResult() == 0) {
			Ciudad ciudad1 = new Ciudad();
			ciudad1.setNombre("Ibague");
			entityManager.persist(ciudad1);

			Ciudad ciudad2 = new Ciudad();
			ciudad2.setNombre("Pasto");
			entityManager.persist(ciudad2);

			Ciudad ciudad3 = new Ciudad();
			ciudad3.setNombre("Pitalito");
			entityManager.persist(ciudad3);

			Map<String, Integer> telefonos = new HashMap<String, Integer>();
			telefonos.put("fijo", 343434);

			Persona p = new Persona(5, "Carlos", "Cacaroto@gmail.com", "coperfil99", "Cra 19 A # 9-16", ciudad2);
			p.setTelefonos(telefonos);
			entityManager.persist(p);

			Persona p1 = new Persona(6, "Sandra", "Sandra@gmail.com", "coperfil89", "Cra 19 A # 9-21", ciudad3);
			p1.setTelefonos(telefonos);
			entityManager.persist(p1);
		}
		
		
		
		

	}

}
