package co.edu.uniquindio.unimotor.prueba;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.unimotor.entidades.Tipocombustible;
import co.edu.uniquindio.unimotor.entidades.Tipovehiculo;
import co.edu.uniquindio.unimotor.entidades.TipovehiculoEnum;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;

import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Fotovehiculo;
import co.edu.uniquindio.unimotor.entidades.Genero;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Telefono;
import co.edu.uniquindio.unimotor.entidades.TipoCombustibleEnum;
import co.edu.uniquindio.unimotor.entidades.Tipocombustible;


@RunWith(Arquillian.class)
public class Modelotest {

	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,"prueba.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml","META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {


	}


	//-------------------------------- JUNIT ENTIDAD CIUDAD ---------------------------------//

	/**
	 * M�todo para persistir una ciudad en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void registrarCiudad(){

		Ciudad c = new Ciudad(5, "Cartagena");
		entityManager.persist(c);

		Ciudad cbuscado = entityManager.find(Ciudad.class, 5);
		Assert.assertNotNull(cbuscado);
	}

	/**
	 * M�todo para buscar una ciudad en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void buscarCiudad(){

		Ciudad c = entityManager.find(Ciudad.class, 5);
		Assert.assertNotNull(c);
	}

	/**
	 * M�todo para actualizar una ciudad en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void actualizarCiudad(){

		Ciudad c = entityManager.find(Ciudad.class, 3);

		c.setNombre("Barranquilla");
		entityManager.merge(c);

		Ciudad cbuscado = entityManager.find(Ciudad.class, 3);
		Assert.assertEquals("Barranquilla", cbuscado.getNombre());

	}

	/**
	 * M�todo para eliminar una ciudad en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void eliminarCiudad(){

		Ciudad c = entityManager.find(Ciudad.class,3);
		entityManager.remove(c);

		Ciudad cbuscado =  entityManager.find(Ciudad.class, 3);
		Assert.assertNull(cbuscado);

	}
	//--------------------------------JUNIT ENTIDAD TIPO COMBUSTIBLE---------------------------------//

	/**
	 * M�todo para persistir un tipo de combustible en la base de datos.
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipocombustible.json"})
	public void registrarTipocombustible(){

		Tipocombustible tc = new Tipocombustible(5, "GAS");
		entityManager.persist(tc);

		Tipocombustible tcbuscado = entityManager.find(Tipocombustible.class, 5);
		Assert.assertNotNull(tcbuscado);

	}

	/**
	 * M�todo para buscar un tipo de combustible en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipocombustible.json"})
	public void buscarTipocombustible(){


		Tipocombustible tc =entityManager.find(Tipocombustible.class, 2);
		Assert.assertNotNull(tc);
	}

	/**
	 * M�todo para actualizar un tipo de combustible en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipocombustible.json"})
	public void actualizarTipocombustible(){

		Tipocombustible tc = entityManager.find(Tipocombustible.class, 3);

		tc.setNombre("Petroleo");
		entityManager.merge(tc);


		Tipocombustible tcbuscado = entityManager.find(Tipocombustible.class, 3);
		Assert.assertEquals("Petroleo", tcbuscado.getNombre());

	}

	/**
	 * M�todo para eliminar un tipo de combustible en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipocombustible.json"})
	public void eliminarTipocombustible(){

		Tipocombustible tc = entityManager.find(Tipocombustible.class,4);
		entityManager.remove(tc);

		Tipocombustible tcbuscado =  entityManager.find(Tipocombustible.class, 4);
		Assert.assertNull(tcbuscado);

	}

	//--------------------------------JUNIT ENTIDAD TIPO VEHICULO---------------------------------//

	/**
	 * M�todo para persistir un tipo de vehiculo en la base de datos.
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipovehiculo.json"})
	public void registrarTipovehiculo(){

		Tipovehiculo tv = new Tipovehiculo(5, "TANQUETA");
		entityManager.persist(tv);

		Tipovehiculo tvbuscado = entityManager.find(Tipovehiculo.class, 5);
		Assert.assertNotNull(tvbuscado);
	}

	/**
	 * M�todo para buscar un tipo de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipovehiculo.json"})
	public void buscarTipovehiculo(){


		Tipovehiculo tv =entityManager.find(Tipovehiculo.class, 2);
		Assert.assertNotNull(tv);
	}

	/**
	 * M�todo para actualizar un tipo de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipovehiculo.json"})
	public void actualizarTipovehiculo(){

		Tipovehiculo tv = entityManager.find(Tipovehiculo.class, 3);

		tv.setNombre("TRACTOR");
		entityManager.merge(tv);


		Tipovehiculo tvbuscado = entityManager.find(Tipovehiculo.class, 3);
		Assert.assertEquals("TRACTOR", tvbuscado.getNombre());

	}

	/**
	 * M�todo para eliminar un tipo de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipovehiculo.json"})
	public void eliminarTipovehiculo(){

		Tipovehiculo tv = entityManager.find(Tipovehiculo.class,1);
		entityManager.remove(tv);

		Tipovehiculo tvbuscado =  entityManager.find(Tipovehiculo.class, 1);
		Assert.assertNull(tvbuscado);

	}

	//--------------------------------JUNIT ENTIDAD MARCA---------------------------------//

	/**
	 * M�todo para persistir una marca de vehiculo en la base de datos.
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void registrarMarca(){

		Marca m = new Marca(5, "BMW");
		entityManager.persist(m);

		Marca mbuscado = entityManager.find(Marca.class, 5);
		Assert.assertNotNull(mbuscado);
	}

	/**
	 * M�todo para buscar una marca de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void buscarMarca(){


		Marca m =entityManager.find(Marca.class, 2);
		Assert.assertNotNull(m);
	}

	/**
	 * M�todo para actualizar una marca de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void actualizarMarca(){

		Marca m = entityManager.find(Marca.class, 3);

		m.setNombre("WOLWSKAGEN");
		entityManager.merge(m);


		Marca mbuscado = entityManager.find(Marca.class, 3);
		Assert.assertEquals("WOLWSKAGEN", mbuscado.getNombre());

	}

	/**
	 * M�todo para eliminar una marca de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void eliminarMarca(){

		Marca m = entityManager.find(Marca.class,1);
		entityManager.remove(m);

		Marca mbuscado =  entityManager.find(Marca.class, 1);
		Assert.assertNull(mbuscado);

	}	

	//--------------------------------JUNIT ENTIDAD CARACTERISTICA---------------------------------//

	/**
	 * M�todo para persistir una caracteristica de un vehiculo en la base de datos.
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"caracteristica.json"})
	public void registrarCaracteristica(){

		Caracteristica car = new Caracteristica(5, "POLARIZADO");
		entityManager.persist(car);

		Caracteristica carbuscado = entityManager.find(Caracteristica.class, 5);
		Assert.assertNotNull(carbuscado);
	}

	/**
	 * M�todo para buscar una caracteristica de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"caracteristica.json"})
	public void buscarCaracteristica(){


		Caracteristica car =entityManager.find(Caracteristica.class, 2);
		Assert.assertNotNull(car);
	}

	/**
	 * M�todo para actualizar una marca en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"caracteristica.json"})
	public void actualizarCaracteristica(){

		Caracteristica car = entityManager.find(Caracteristica.class, 3);

		car.setNombre("VIDRIO EN CRISTAL");
		entityManager.merge(car);


		Caracteristica carbuscado = entityManager.find(Caracteristica.class, 3);
		Assert.assertEquals("VIDRIO EN CRISTAL", carbuscado.getNombre());

	}

	/**
	 * M�todo para eliminar una caracteristica de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"caracteristica.json"})
	public void eliminarCaracteristica(){

		Caracteristica car = entityManager.find(Caracteristica.class,1);
		entityManager.remove(car);

		Caracteristica carbuscado =  entityManager.find(Caracteristica.class, 1);
		Assert.assertNull(carbuscado);

	}


	//-------------------------------- JUNIT ENTIDAD TELEFONO ---------------------------------//

	/**
	 * M�todo para persistir un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","telefono.json","ciudad.json"})
	public void registrarTelefono(){


		Telefono t = new Telefono(5, "Mongolia", 321844597);
		entityManager.persist(t);

		Telefono tbuscado = entityManager.find(Telefono.class, 5);
		Assert.assertNotNull(tbuscado);


	}
	/**
	 * M�todo para buscar un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","telefono.json","ciudad.json"})
	public void buscarTelefono(){

		Telefono t =  entityManager.find(Telefono.class, 4);
		Assert.assertNotNull(t);
	}
	/**
	 * M�todo para actualizar un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","telefono.json","ciudad.json"})
	public void actualizarTelefono(){

		Telefono t =  entityManager.find(Telefono.class, 2);
		t.setNumero(315525894);
		entityManager.merge(t);

		Telefono tbuscado = entityManager.find(Telefono.class, 2);
		Assert.assertEquals(315525894, tbuscado.getNumero());


	}
	/**
	 * M�todo para eliminar un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","telefono.json","ciudad.json"})
	public void eliminarTelefono(){

		Telefono t =entityManager.find(Telefono.class, 3);

		entityManager.remove(t);

		Telefono tbuscado =entityManager.find(Telefono.class, 3);

		Assert.assertNull(tbuscado);
	}
	//-------------------------------- JUNIT ENTIDAD PERSONA ---------------------------------//
	/**
	 * M�todo para persistir una persona en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void registrarPersona(){

		Persona p = new Persona(457895, "Oscar Polania", "OscarP@gmail.com", "Zoonosis", "Los cerros");
		entityManager.persist(p);

		Persona pbuscado = entityManager.find(Persona.class, 457895);
		Assert.assertNotNull(pbuscado);
	}

	/**
	 * M�todo para buscar una persona registrada en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void buscarPersona () {

		Persona p = entityManager.find(Persona.class, 13818457);
		Assert.assertNotNull(p);
	}

	/**
	 * M�todo para actualizar una persona registrada en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void actualizarPersona () {

		Persona p = entityManager.find(Persona.class, 1094922231);

		p.setGenero(Genero.FEMENINO);
		entityManager.merge(p);

		Persona pbuscado = entityManager.find(Persona.class, 1094922231);

		Assert.assertEquals(Genero.FEMENINO, pbuscado.getGenero());
	}

	/**
	 * M�todo para eliminar una persona registrada en la base de datos mediante su PK.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void eliminarPersona () {

		Persona p =entityManager.find(Persona.class, 1094934815);
		entityManager.remove(p);

		Persona pbuscado = entityManager.find(Persona.class, 1094934815);
		Assert.assertNull(pbuscado);

	}

	//-------------------------------- JUNIT ENTIDAD VEHICULO---------------------------------//
	/**
	 * M�todo para persistir un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json","marca.json","tipovehiculo.json","tipocombustible.json","favorito.json","caracteristica.json","fotovehiculo.json","pregunta.json"})
	public void registraVehiculo(){
		Persona p = new Persona(1094931589, "Esteban", "d@gmail.com", "acromatopsia", "La caba�a");
		Ciudad c = new Ciudad(5, "Bogota");
		Marca m = new Marca(5, "Mclaren");


		Caracteristica car2 = new Caracteristica(5, "Blindado");
		Caracteristica car =  new Caracteristica(6, "Vidrios oscuros");
		List <Caracteristica> caracteristicas = new ArrayList <>();
		caracteristicas.add(car);
		caracteristicas.add(car2);

		Fotovehiculo foto1= entityManager.find(Fotovehiculo.class, 1);
		List <Fotovehiculo> fotosVehiculos = new ArrayList <>();
		fotosVehiculos.add(foto1);




		Vehiculo v = new Vehiculo(4, 70000000,"Excelente estado", "Negro", 2000, 2500, 5, TipoCombustibleEnum.DIESEL, TipovehiculoEnum.CAMIONETA, p, c, m, null, null, fotosVehiculos ,caracteristicas );

		entityManager.persist(v);

		Vehiculo vbuscado = entityManager.find(Vehiculo.class, 4);
		Assert.assertNotNull(vbuscado);
	}

	/**
	 * M�todo para buscar un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json","vehiculo.json","marca.json","tipovehiculo.json","tipocombustible.json"})
	public void buscarVehiculo () {

		Vehiculo v = entityManager.find(Vehiculo.class, 2);

		Assert.assertNotNull(v);
		System.out.print(v);
	}

	/**
	 * M�todo para actualizar un vehiculo registrado en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json","marca.json","tipovehiculo.json","tipocombustible.json","vehiculo.json"})
	public void actualizarVehiculo () {

		Vehiculo v = entityManager.find(Vehiculo.class, 2);

		v.setAnio(2003);
		entityManager.merge(v);

		Vehiculo vbuscado = entityManager.find(Vehiculo.class, 2);
		Assert.assertEquals(2003,vbuscado.getAnio());
	}

	/**
	 * M�todo para eliminar un vehiculo registrado en la base de datos mediante su PK.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json","marca.json","tipovehiculo.json","tipocombustible.json","vehiculo.json"})
	public void eliminarVehiculo () {

		Vehiculo v =entityManager.find(Vehiculo.class, 2);
		entityManager.remove(v);

		Vehiculo vbuscado = entityManager.find(Vehiculo.class, 2);
		Assert.assertNull(vbuscado);

	}


	//-------------------------------- JUNIT ENTIDAD PREGUNTA ---------------------------------//

	/**
	 * M�todo para persistir una pregunta en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json"})
	public void registrarPregunta(){


		Pregunta p = new Pregunta(4, "Quien es el propietario de la nave", null);
		entityManager.persist(p);

		Pregunta pbuscado = entityManager.find(Pregunta.class, 4);
		Assert.assertNotNull(pbuscado);


	}
	/**
	 * M�todo para buscar una Pregunta en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","pregunta.json"})
	public void buscarPregunta(){

		Pregunta p =  entityManager.find(Pregunta.class, 2);
		Assert.assertNotNull(p);
	}
	/**
	 * M�todo para actualizar un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","pregunta.json"})
	public void actualizarPregunta(){

		Pregunta p =  entityManager.find(Pregunta.class, 2);
		p.setTexto_de_la_pregunta("Sera posible hacer un trueque con mi carro actual");
		entityManager.merge(p);

		Pregunta pbuscado = entityManager.find(Pregunta.class, 2);
		Assert.assertEquals("Sera posible hacer un trueque con mi carro actual", pbuscado.getTexto_de_la_pregunta());


	}

	/**
	 * M�todo para eliminar una persona registrada en la base de datos mediante su PK.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","pregunta.json"})
	public void eliminarPregunta() {

		Pregunta p =entityManager.find(Pregunta.class, 3);
		entityManager.remove(p);

		Pregunta pbuscado = entityManager.find(Pregunta.class, 3);
		Assert.assertNull(pbuscado);
	}	

	//-------------------------------- JUNIT ENTIDAD FAVORITO ---------------------------------//

	/**
	 * M�todo para persistir un favorito en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json"})
	public void registrarFavorito(){


		Favorito f = new Favorito(4);
		entityManager.persist(f);

		Favorito fbuscado = entityManager.find(Favorito.class, 4);
		Assert.assertNotNull(fbuscado);


	}
	/**
	 * M�todo para buscar un favorito en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","favorito.json"})
	public void buscarFavorito(){

		Favorito f =  entityManager.find(Favorito.class, 2);
		Assert.assertNotNull(f);
	}
	/**
	 * M�todo para actualizar un favorito en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","favorito.json"})
	public void actualizarFavorito(){

		Favorito f =  entityManager.find(Favorito.class, 2);
		f.setId(5);
		entityManager.merge(f);

		Favorito fbuscado = entityManager.find(Favorito.class, 2);
		Assert.assertEquals(5, fbuscado.getId());


	}

	/**
	 * M�todo para eliminar un favorito registrado en la base de datos mediante su PK.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","favorito.json"})
	public void eliminarFavorito() {

		Favorito f =entityManager.find(Favorito.class, 3);
		entityManager.remove(f);

		Favorito fbuscado = entityManager.find(Favorito.class, 3);
		Assert.assertNull(fbuscado);
	}	

	//-------------------------------- JUNIT ENTIDAD FOTO VEHICULO ---------------------------------//

	/**
	 * M�todo para persistir una foto de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json"})
	public void registrarFotovehiculo(){


		Fotovehiculo f = new Fotovehiculo(4, "www.wikipedia.co");
		entityManager.persist(f);

		Fotovehiculo fbuscado = entityManager.find(Fotovehiculo.class, 4);
		Assert.assertNotNull(fbuscado);


	}
	/**
	 * M�todo para buscar una foto de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json"})
	public void buscarFotovehiculo(){

		Fotovehiculo f =  entityManager.find(Fotovehiculo.class, 2);
		Assert.assertNotNull(f);
	}
	/**
	 * M�todo para actualizar un dato de la foto de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json"})
	public void actualizarFotovehiculo(){

		Fotovehiculo f =  entityManager.find(Fotovehiculo.class, 2);
		f.setUrl("www.tucarro.com");
		entityManager.merge(f);

		Fotovehiculo fbuscado = entityManager.find(Fotovehiculo.class, 2);
		Assert.assertEquals("www.tucarro.com", fbuscado.getUrl());


	}

	/**
	 * M�todo para eliminar una foto de un vehiculo en la base de datos mediante su PK.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json"})
	public void eliminarFotovehiculo() {

		Fotovehiculo f =entityManager.find(Fotovehiculo.class, 3);
		entityManager.remove(f);

		Fotovehiculo fbuscado = entityManager.find(Fotovehiculo.class, 3);
		Assert.assertNull(fbuscado);
	}	
}
