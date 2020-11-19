package co.edu.uniquindio.unimotor.prueba;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;

@RunWith(Arquillian.class)
public class NegocioTest {

	@PersistenceContext
	private EntityManager entityManager;


	@EJB
	private UnimotorEJB unimotorEJB;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(UnimotorEJB.class)
				.addPackage(Vehiculo.class.getPackage())
				.addAsResource("persistenceForTest.xml","META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	/**
	 * Método para probar cuando se guarda un vehiculo como favorito.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","favorito.json"})
	public void testGuardarVehiculoFavorito () {

		Cliente cliente =  entityManager.find(Cliente.class, 40728440);
	
		Vehiculo v = entityManager.find(Vehiculo.class, 3);
        Assert.assertEquals(cliente.getFavoritos().size(), 0);


		try {
			unimotorEJB.guardarVehiculoComoFavorito(v, cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

      Assert.assertEquals(cliente.getFavoritos().size(), 1);




	}
	
	/**
	 * Método para probar cuando se elimina un vehiculo como favorito.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","favorito.json"})
	public void testEliminarVehiculoFavorito () {

		Cliente cliente =  entityManager.find(Cliente.class, 1094922231);
		Vehiculo v = entityManager.find(Vehiculo.class, 2);
		
		
		Assert.assertEquals(1, cliente.getFavoritos().size());
		
	
       try {
    	   
    	   unimotorEJB.eliminarVehiculoComoFavorito(v, cliente);
	} catch (NullPointerException e) {//Solo va entrar aquí si sale un error de null pointer exception. Si sale otro tipo de error no va entrar al cash.
		
		System.out.println("Eliminaste todos los favoritos de los vehiculos marcados");
	}
        
        
        Assert.assertEquals (0,cliente.getFavoritos().size());
        
       }
	
	
	/**
	 * Método para probar cuando se elimina un vehiculo como favorito.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","favorito.json","pregunta.json"})
	public void testAgregarPreguntaPorVehiculo() {
		
	Persona persona1 = entityManager.find(Persona.class, 1094934815);
	Vehiculo vehiculo = entityManager.find(Vehiculo.class, 1);

     Pregunta p = new Pregunta("Cuantas veces ha sido estrellado", persona1, vehiculo);
     System.out.println(vehiculo.getPreguntas().size());
     Assert.assertEquals(1, vehiculo.getPreguntas().size());
     unimotorEJB.realizarPreguntaVehiculo(p);
     System.out.println(vehiculo.getPreguntas().size());
     Assert.assertEquals(2, vehiculo.getPreguntas().size());
}
	/**
	 * Método para eliminar una pregunta de un Vehiculo.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","favorito.json","pregunta.json"})
	public void testEliminarPreguntaPorVehiculo() {
	
	Pregunta pregunta= entityManager.find(Pregunta.class, 2);
		
		Assert.assertEquals(1, pregunta.getVehiculo().getPreguntas().size());
		
		try {
			
			unimotorEJB.eliminarPreguntaVehiculo(pregunta);
			Assert.assertEquals(0, pregunta.getVehiculo().getPreguntas().size());
		} catch (NullPointerException e) {
			System.out.println("Este vehiculo ya no cuenta con ninguna pregunta");
		}
	}
	
	/**
	 * Método para responder una pregunta referente a un vehiculo de una persona.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","favorito.json","pregunta.json"})
	public void testResponderPregunta() {
		
		
		Pregunta pregunta = entityManager.find(Pregunta.class, 3);
		Assert.assertNull(pregunta.getRespuesta());
		
		
		try {
			unimotorEJB.responderPregunta(pregunta, "Si tenemos carros disponibles Marca Hyundai desde ano 2018");
			Assert.assertNotNull(pregunta.getRespuesta());
		} catch (Exception e) {
			
		}
		
	}
}