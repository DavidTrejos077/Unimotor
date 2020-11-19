package co.edu.uniquindio.unimotor.prueba;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

import co.edu.uniquindio.unimotor.dto.ConsultaVehiculoCaracteristicasDTO;
import co.edu.uniquindio.unimotor.dto.ConsultaVehiculosPorCadaMarcaDTO;
import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Favorito;
import co.edu.uniquindio.unimotor.entidades.Fotovehiculo;
import co.edu.uniquindio.unimotor.entidades.Genero;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.OpcionNuevoUsado;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Pregunta;
import co.edu.uniquindio.unimotor.entidades.Telefono;
import co.edu.uniquindio.unimotor.entidades.TipoCombustibleEnum;
import co.edu.uniquindio.unimotor.entidades.TipovehiculoEnum;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;




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
	 * Método para persistir una ciudad en la base de datos.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void registrarCiudad(){

		/*Ciudad c = new Ciudad(5, "Cartagena");
		entityManager.persist(c);

		Ciudad cbuscado = entityManager.find(Ciudad.class, 5);
		Assert.assertNotNull(cbuscado);*/
	}

	/**
	 * Método para buscar una ciudad en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"ciudad.json"})
	public void buscarCiudad(){

		Ciudad c = entityManager.find(Ciudad.class, 4);
		Assert.assertNotNull(c);
	}

	/**
	 * Método para actualizar una ciudad en la base de datos.
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
	 * Método para eliminar una ciudad en la base de datos.
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
	
	 * Método para eliminar un tipo de combustible en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipocombustible.json"})
	public void eliminarTipocombustible(){

		TipoCombustibleEnum tc = entityManager.find(TipoCombustibleEnum.class,4);
		entityManager.remove(tc);

		TipoCombustibleEnum tcbuscado =  entityManager.find(TipoCombustibleEnum.class, 4);
		Assert.assertNull(tcbuscado);

	}

	//--------------------------------JUNIT ENTIDAD TIPO VEHICULO---------------------------------//

	
	

		
	
	


	/**
	
	 * Método para eliminar un tipo de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"tipovehiculo.json"})
	public void eliminarTipovehiculo(){

	
		TipovehiculoEnum tv = entityManager.find(TipovehiculoEnum.class,1);
		entityManager.remove(tv);


		TipovehiculoEnum tvbuscado =  entityManager.find(TipovehiculoEnum.class, 1);
		Assert.assertNull(tvbuscado);

	}

	//--------------------------------JUNIT ENTIDAD MARCA---------------------------------//

	/**
	 * Método para persistir una marca de vehiculo en la base de datos.
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void registrarMarca(){

		/*Marca m = new Marca(5, "BMW");
		entityManager.persist(m);

		Marca mbuscado = entityManager.find(Marca.class, 5);

		Assert.assertNotNull(mbuscado);*/
	}

	/**
	 * Método para buscar una marca de vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"marca.json"})
	public void buscarMarca(){


		Marca m =entityManager.find(Marca.class, 2);
		Assert.assertNotNull(m);
	}

	/**
	 * Método para actualizar una marca de vehiculo en la base de datos.
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
	 * Método para eliminar una marca de vehiculo en la base de datos.
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
	 * Método para persistir una caracteristica de un vehiculo en la base de datos.
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
	 * Método para buscar una caracteristica de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"caracteristica.json"})
	public void buscarCaracteristica(){


		Caracteristica car =entityManager.find(Caracteristica.class, 2);
		Assert.assertNotNull(car);
	}

	/**
	 * Método para actualizar una marca en la base de datos.
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
	 * Método para eliminar una caracteristica de un vehiculo en la base de datos.
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
	 * Método para persistir un telefono en la base de datos.
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
	 * Método para buscar un telefono en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","telefono.json","ciudad.json"})
	public void buscarTelefono(){

		Telefono t =  entityManager.find(Telefono.class, 4);
		Assert.assertNotNull(t);
	}
	/**
	 * Método para actualizar un telefono en la base de datos.
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
	 * Método para eliminar un telefono en la base de datos.
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
	 * Método para persistir una persona en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void registrarPersona(){
		/*Ciudad c = new Ciudad(5, "Bogota");
		Persona p = new Persona();
		entityManager.persist(p);

		Persona pbuscado = entityManager.find(Persona.class, 457895);
		Assert.assertNotNull(pbuscado);*/
	}

	/**
	 * Método para buscar una persona registrada en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json"})
	public void buscarPersona () {

		Persona p = entityManager.find(Persona.class, 13818457);
		Assert.assertNotNull(p);
	}

	/**
	 * Método para actualizar una persona registrada en la base de datos.
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
	 * Método para eliminar una persona registrada en la base de datos mediante su PK.
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
	 * Método para persistir un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","ciudad.json","marca.json","tipovehiculo.json","tipocombustible.json","favorito.json","caracteristica.json","fotovehiculo.json","pregunta.json"})
	
	public void registraVehiculo(){
		/*Ciudad c = new Ciudad(5, "Bogota");
		Cliente p = new Cliente(); //deben corregir esto, de acuerdo a la ifno que recibe el constructor
		
		Marca marca1 = new Marca(5, "Ferrari");
		Modelo m = new Modelo(5, "CAPTUR");
		
		
		


		Caracteristica car2 = new Caracteristica(5, "Blindado");
		Caracteristica car =  new Caracteristica(6, "Vidrios oscuros");
		List <Caracteristica> caracteristicas = new ArrayList <>();
		caracteristicas.add(car);
		caracteristicas.add(car2);

		Fotovehiculo foto1= entityManager.find(Fotovehiculo.class, 1);
		List <Fotovehiculo> fotosVehiculos = new ArrayList <>();
		fotosVehiculos.add(foto1);



     
		Vehiculo v = new Vehiculo(4,"Kia sportage revolution xt25" ,70000000,"WNG750" ,50000,"Excelente estado", "Negro", 2000, 2500, 5, TipoCombustibleEnum.DIESEL  , Tranmision.MECANICA,  OpcionNuevoUsado.NUEVO, TipovehiculoEnum.CAMIONETA,  p, c ,marca1,m, fotosVehiculos  ,caracteristicas );
		
		entityManager.persist(v);

		Vehiculo vbuscado = entityManager.find(Vehiculo.class, 4);
		Assert.assertNotNull(vbuscado);*/
		
	}

	/**
	 * Método para buscar un vehiculo en la base de datos.
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
	 * Método para actualizar un vehiculo registrado en la base de datos.
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
	 * Método para eliminar un vehiculo registrado en la base de datos mediante su PK.
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
	 * Método para persistir una pregunta en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json"})
	public void registrarPregunta(){


		Pregunta p = new Pregunta( "Quien es el propietario de la nave", null,null);
		entityManager.persist(p);

		Pregunta pbuscado = entityManager.find(Pregunta.class, 4);
		Assert.assertNotNull(pbuscado);


	}
	/**
	 * Método para buscar una Pregunta en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","pregunta.json"})
	public void buscarPregunta(){

		Pregunta p =  entityManager.find(Pregunta.class, 2);
		Assert.assertNotNull(p);
	}
	/**
	 * Método para actualizar un telefono en la base de datos.
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
	 * Método para eliminar una persona registrada en la base de datos mediante su PK.
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
	 * Método para persistir un favorito en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json"})
	public void registrarFavorito(){

        Cliente cliente = new  Cliente();
        Vehiculo v = new Vehiculo ();
		Favorito f = new Favorito(cliente, v);
		entityManager.persist(f);

		Favorito fbuscado = entityManager.find(Favorito.class, 4);
		Assert.assertNotNull(fbuscado);


	}
	/**
	 * Método para buscar un favorito en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json","vehiculo.json","favorito.json"})
	public void buscarFavorito(){

		Favorito f =  entityManager.find(Favorito.class, 2);
		Assert.assertNotNull(f);
	}
	/**
	 * Método para actualizar un favorito en la base de datos.
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
	 * Método para eliminar un favorito registrado en la base de datos mediante su PK.
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
	 * Método para persistir una foto de un vehiculo en la base de datos.
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
	 * Método para buscar una foto de un vehiculo en la base de datos.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json"})
	public void buscarFotovehiculo(){

		Fotovehiculo f =  entityManager.find(Fotovehiculo.class, 2);
		Assert.assertNotNull(f);
	}
	/**
	 * Método para actualizar un dato de la foto de un vehiculo en la base de datos.
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
	 * Método para eliminar una foto de un vehiculo en la base de datos mediante su PK.
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
	
	//-------------------------------- CONSULTAS DE LA ENTIDAD VEHICULO ---------------------------------//
	
	
	/**
	 * Consulta para traer los vehiculos.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void testLista () {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("TODOS_VEHICULOS", Vehiculo.class);
		
		q.setMaxResults(3); //Con esto se construyen los paginadores, por cada consulta traigame en este caso 3 registros
		q.setFirstResult(0);
		List<Vehiculo>  l = q.getResultList();
		
		for (Vehiculo v : l) {
			
			System.out.println(v.getColor()+""+v.getDescripcion());
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer los vehiculos de una ciudad específica.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void testListaVehiculoCiudad () {
		Ciudad c = new Ciudad();
		c.setId(1);
		
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("TODOS_VEHICULOS_CIUDAD", Vehiculo.class);
		
		q.setParameter(1, c );
		List<Vehiculo>  l = q.getResultList();
		
		
			
for (Vehiculo v : l) {
			
			System.out.println(v.getColor()+""+v.getDescripcion());
		}
	
		
}
	
	
	
	
	/**
	 * Consulta para traer la descripción de un vehiculo de acuerdo a un color
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void vehiculoColorTest () {
		TypedQuery<String> q = entityManager.createNamedQuery("VEHICULO_DESCRIPCION" , String.class);
		
		q.setParameter("color", "Amarillo");
		List<String>  l = q.getResultList();
		
		for (String v : l) {
			
			System.out.println(v);
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer los vehiculos de acuerdo a un rango de años estipulados.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void testListaRangoAnioVehiculo () {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("TODOS_VEHICULOS_ANIO" , Vehiculo.class);
		
		
		List<Vehiculo>  l = q.getResultList();
		
		for (Vehiculo v : l) {
			
			System.out.println(v);
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer los vehiculos de acuerdo a su atributo color
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void testListaVehiculo () {
		TypedQuery<Object[]> q = entityManager.createNamedQuery("VEHICULO_COLOR" , Object[].class);
		q.setParameter("color", "Morado");
		
		List<Object[]>  l = q.getResultList();
		
		for (Object[] v : l) {
			
			System.out.println(v[0]+ " "+ v[1]);
		}
		
		
		
	}
	
	
	
	
	
	
	//-------------------------------- CONSULTAS DE LA ENTIDAD VEHICULO---------------------------------//
	
	
	/**
	 * Consulta para traer los vehiculos favoritos de la lista de favoritos de una persona.
	 */

 /*
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","favorito.json","telefono.json"})
	public void testListaFavoritosPersona () {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("LISTA_FAVORITOS_PERSONA2",Vehiculo.class);
		q.setParameter("email", "davidtrejoscortes@gmail.com");
		
		List<Vehiculo>  l = q.getResultList();
		
		for (Vehiculo v : l) {
			
			System.out.println(v);
		}
		
		
		
	}
	
	*/
	
	/**
	 * Consulta para determinar cuantos vehiculos hay de cada tipo.
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json"})
	public void testListaVehiculosCadaTipo () {
		TypedQuery<Object[]> q = entityManager.createNamedQuery("NUMERO_DE_VEHICULOS_POR_TIPO", Object[].class);
		
		
		List<Object[]>  l = q.getResultList();
		
		for (Object[] v : l) {
			
			System.out.println(v[0] + " "+ v[1]+ " "+ v[2]);
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer la lista de preguntas referente a un vehiculo que fue realizada por una persona
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","favorito.json","telefono.json","pregunta.json"})
	public void testListaPreguntasVehiculoTest () {
		TypedQuery<Object[]> q = entityManager.createNamedQuery("VEHICULO_PREGUNTAS",Object[].class);
		
		
		List<Object[]>  l = q.getResultList();
		
		for (Object[] v : l) {
			
			System.out.println(v[0]+" "+v[1]);
		}
		
		
		
	}
	
	/**
	 * Consulta para traer las personas que realizaron preguntas sobre un vehiculo especifico
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","favorito.json","telefono.json","pregunta.json"})
	public void testListaPersonasPreguntasTest () {
		TypedQuery<Persona> q = entityManager.createNamedQuery("LISTA_PERSONAS_PREGUNTA_VEHICULO",Persona.class);
		q.setParameter("id", 2);
		
		List<Persona>  l = q.getResultList();
		
		for (Persona p : l) {
			
			System.out.println(p);
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer la lista personas de una respectiva ciudad
	 */
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","favorito.json","telefono.json","pregunta.json"})
	public void testListaPersonasCiudad () {
		TypedQuery<Object[]> q = entityManager.createNamedQuery( "LISTADO_PERSONAS_CIUDAD",Object[].class);
		q.setParameter("nombre","Pereira");
		
		List<Object[]>  l = q.getResultList();
		
		for (Object[] p : l) {
			
			System.out.println(p[0]+" "+p[1]+" "+p[2]);
		}
		
		
		
	}
	
	/**
	 * Consulta para traer la lista de caracteristicas de acuerdo a un carro especifico
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","favorito.json","telefono.json","pregunta.json"})
	public void testListaCaracteristicaVehiculo () {
		TypedQuery<Caracteristica> q = entityManager.createNamedQuery("LISTA_VEHICULO_CARACTERISTICAS",Caracteristica.class);
		q.setParameter("id", 2);
		
		List<Caracteristica>  l = q.getResultList();
		
		for (Caracteristica c : l) {
			
			System.out.println(c);
		}
		
		
		
	}
	
	
	
	/**
	 * Consulta para traer las fotos de un vehiculo.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json"})
	public void testListaFotosVehiculo () {
		TypedQuery<Object[]> q = entityManager.createNamedQuery("VEHICULO_FOTOS",Object[].class);
		q.setParameter("marca", "Kia");
		q.setParameter("precioMax", 17500000);
		q.setParameter("precioMin" ,7500000);
		
		
		List<Object[]>  l = q.getResultList();
		
		for (Object[] v : l) {
			
			System.out.println(v[0]+ " "+v[1]);
		}
		
		
		
	}
	
	
	/**
	 * Consulta para traer las caracteristicas de un vehiculo.
	 */

	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
	public void testListaCaracteristicasVehiculo () {
		
		
ArrayList <String> lista = new  ArrayList <String>();
		
		lista.add("Rines de lujo");
		lista.add("Pelicula de seguridad");
		lista.add("Vidrios electricos");
		
		
		TypedQuery<ConsultaVehiculoCaracteristicasDTO> q = entityManager.createNamedQuery("VEHICULO_CARACTERISTICAS",ConsultaVehiculoCaracteristicasDTO.class);
		
		q.setParameter("lista", lista);
		
		
		
		
		
		List<ConsultaVehiculoCaracteristicasDTO>  l = q.getResultList();
		
		for (ConsultaVehiculoCaracteristicasDTO v : l) {
			
			System.out.println(v);
		}
		
		
		
	}
	
	
	
	/**
	 * Método para contar el numero de vehiculos registrados con una caracteristica especifica mediante una consulta.
	 */
	
	
	
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
	public void testNumeroVehiculosUnicaCaracteristica () {
		
		
		TypedQuery<Long> q = entityManager.createNamedQuery("NUMERO_DE_VEHICULOS_CON_UNA_CARACTERISTICA_ESPECIFICA", Long.class);
		
		q.setParameter("id", 2);
		
		
		List<Long>  l = q.getResultList();
		
            for (Long v : l) {
            	
            
			
			System.out.println(v);
		}
	}
            /**
        	 * Método para contar el numero de vehiculos registrados por cada marca mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
        	public void testNumeroVehiculosPorCadaMarca () {
        		
        		
        		TypedQuery<ConsultaVehiculosPorCadaMarcaDTO> q = entityManager.createNamedQuery("NUMERO_DE_VEHICULOS_POR_CADA_MARCA",ConsultaVehiculosPorCadaMarcaDTO.class);
        		
        		
        		List<ConsultaVehiculosPorCadaMarcaDTO>  l = q.getResultList();
        		
                    for (ConsultaVehiculosPorCadaMarcaDTO v : l) {
                    	
                    
        			
        			System.out.println(v.getId() + " " + v.getNombreMarca()+ " " + v.getCantidadVehiculos());
                    }
	
        	}
        	
        	
        	/**
        	 * Método para obtener el promedio de los vehiculos que sean de una respectiva marca, nuevo o usado, un rango de kilometraje y una ciudad especifica mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
        	public void testPromedioVehiculosConEspecificacion () {
        		
        		
        		TypedQuery<Double> q = entityManager.createNamedQuery("VALOR_PROMEDIO_DE_VEHICULOS", Double.class);
        		
        		q.setParameter("marca", 2);
        		//q.setParameter("tipoCarro", OpcionNuevoUsado.NUEVO);
        		//q.setParameter("tipoCarro2", OpcionNuevoUsado.USADO);
        		q.setParameter("precioMax", 80000);
        		q.setParameter("precioMin" ,30000);
        		q.setParameter("ciudad", 2);
        		
        		List<Double>  l = q.getResultList();
        		
                    for (Double v : l) {
                    	
                    
        			
        			System.out.println(v);
        		}
        	}
        	

        	/**
        	 * Método para obtener la lista de vehiculos que contiene mayor numero de caracteristicas mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
        	public void testListaVehiculosConMasCaracteristicas () {
        		
        		
        		TypedQuery<Object[]> q = entityManager.createNamedQuery("LISTA_VEHICULOS_CON_TODAS_LAS_CARACTERISTICAS",Object[].class);
        		
        		
        		
        		List<Object[]>  l = q.getResultList();
        		
                    for (Object[] v : l) {
                    
        			
        			System.out.println(v[0]+ " "+ v[1]);
        		}
        	}
        	
        	
        	
        	/**
        	 * Método para obtener el vehiculo nuevo más costoso de  una determinada ciudad mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json"})
        	public void testVehiculoNuevoMasCostoso () {
        		
        		
        		TypedQuery<Object[]> q = entityManager.createNamedQuery("VEHICULO_NUEVO_MAS_COSTOSO",Object[].class);
        		
        		q.setParameter("tipoCarro", OpcionNuevoUsado.NUEVO);
        		q.setParameter("ciudad", 2);
        		
        		List<Object[]>  l = q.getResultList();
        		
                    for (Object[] v : l) {
                    
        			
        			System.out.println(v[0]+ " "+v[1]);
        		}
        	}
        	
        	
        	/**
        	 * Método para contar el numero de vehiculos registrados con una caracteristica especifica mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testVehiculosSinPreguntas () {
        		
        		
        		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("LISTA_VEHICULOS_SIN_PREGUNTA", Vehiculo.class);
        		
        		
        		
        		
        		List<Vehiculo>  l = q.getResultList();
        		
                    for (Vehiculo v : l) {
                    	
                    
        			
        			System.out.println(v);
        		}
        	}
        	
        	/**
        	 * Método para obtener el precio del vehiculo más costoso por cada marca mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testVehiculoMasCostosoPorCadaMarca () {
        		
        		
        		TypedQuery<Long > q = entityManager.createNamedQuery("VEHICULO_MAS_COSTOSO_POR_CADA_MARCA", Long.class);
        		
        		
        		
        		
        		List<Long>  l = q.getResultList();
        		
                    for (Long v : l) {
                    	
                    
        			
        			System.out.println(v);
        		}
        	}
        	
        	
        	/**
        	 * Método para devolver la cantidad total de KM recorridos por cada modelo de vehiculo mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testKilometrosRecorridosPorModeloVehiculo() {
        		
        		
        		TypedQuery<Long> q = entityManager.createNamedQuery("KILOMETROS_RECORRIDOS_POR_MODELO", Long.class);
        		
        		
        		
        		
        		List<Long>  l = q.getResultList();
        		
                    for (Long v : l) {
                    	
                    
        			
        			System.out.println(v);
        		}
        	}
        	/**
        	 * Método que devuelve la cantidad de vehiculos por cada modelo & ordena la lista asc con base en el nombre de la marca mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testCantidadVehiculoPorModelo() {
        		
        		
        		TypedQuery<Long> q = entityManager.createNamedQuery("CANTIDAD_VEHICULOS_POR_MODELO", Long.class);
        		
        		
        		
        		
        		List<Long>  l = q.getResultList();
        		
                    for (Long v : l) {
                    	
                    
        			
        			System.out.println(v);
        		}
        	}
        	
        	/**
        	 * Método que devuelve la caracteristica mas comun que tiene todos los vehiculos mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testCaracteristicaComunVehiculos() {
        		
        		
        		TypedQuery<String> q = entityManager.createNamedQuery( "CARACTERISTICA_COMUN_VEHICULOS", String.class);
        		
        		
        		
        		
        		List<String>  l = q.getResultList();
        		
                    for (String c : l) {
                    	
                    
        			
        			System.out.println(c);
        		}
        	}
        	
     //-------------------------------- CONSULTAS DE LA ENTIDAD CIUDAD ---------------------------------//    	
        	
	
        	/**
        	 * Método para obtener la ciudad que tiene más vehiculos mediante una consulta.
        	 */
        	
        	
        	
        	@Test
        	@Transactional(value=TransactionMode.ROLLBACK)
        	@UsingDataSet({"vehiculo.json","fotovehiculo.json","caracteristica.json","marca.json","persona.json","tipocombustible.json","tipovehiculo.json","modelo.json", "ciudad.json","telefono.json","vehiculo_caracteristica.json","pregunta.json"})
        	public void testCiudadConMasVehiculosParaVenta () {
        		
        		
        		TypedQuery<String> q = entityManager.createNamedQuery("CIUDAD_CON_MAS_VEHICULOS_PARA_VENTA", String.class);
        		
        		
        		
        		
        		List<String>  l = q.getResultList();
        		
                    for (String c : l) {
                    	
                    
        			
        			System.out.println(c);
        		}
        	}
	
		
	//-------------------------------- CONSULTAS DE LA ENTIDAD PERSONA ---------------------------------//
	
		@Test
		@Transactional(value=TransactionMode.ROLLBACK)
		@UsingDataSet({"persona.json","ciudad.json"})
		public void testListaPersonas () {
			
			
			TypedQuery<Persona> q = entityManager.createNamedQuery("TODAS_PERSONAS", Persona.class);
			
			
			List<Persona>  l = q.getResultList();
			
			
			
	       for (Persona p : l) {
				
				System.out.println(p.getNombre()+ " "+ p.getDireccion()+ " "+ p.getEmail());
			}
		
		}
		
		/**
		 * Método para devolver a qué persona le pertenece un telefono mediante una consulta
		 */
		
		@Test
		@Transactional(value=TransactionMode.ROLLBACK)
		@UsingDataSet({"persona.json","ciudad.json"})
		public void testPersonaTelefono () {
			
			
			TypedQuery<Persona> q = entityManager.createNamedQuery("BUSCAR_PERSONA_TELEFONO", Persona.class);
			q.setParameter("telefonoFijo", 7458821);
			
			List<Persona>  l = q.getResultList();
			
	       for (Persona p : l) {
				
				System.out.println(p);
			}
		
		}
		
		
		
		
		/**
		 * Método para autenticar una persona con su email y clave mediante una consulta
		 */
		
		
		
		@Test
		@Transactional(value=TransactionMode.ROLLBACK)
		@UsingDataSet({"persona.json","ciudad.json"})
		public void testAutenticacionPersona () {
			
			
			TypedQuery<Persona> q = entityManager.createNamedQuery("AUTENTICAR_PERSONA", Persona.class);
			
			q.setParameter("email", "davidtrejoscortes@gmail.com" );
			q.setParameter("clave", "Coperfil08" );
			List<Persona>  l = q.getResultList();
			
			Assert.assertEquals(1, l.size());
		
		}
		
		
		/**
		 * Método para traer la  lista de personas registradas y ordenarlas alfabeticamente mediante una consulta.
		 */
		
		
		
		@Test
		@Transactional(value=TransactionMode.ROLLBACK)
		@UsingDataSet({"persona.json","ciudad.json"})
		public void testListaPersonasOrdenadas () {
			
			
			TypedQuery<Persona> q = entityManager.createNamedQuery("LISTA_PERSONAS_ORDENADAS_ALFABETICAMENTE", Persona.class);
			
			
			List<Persona>  l = q.getResultList();
			
                for (Persona p : l) {
				
				System.out.println(p.getNombre());
			}
		
		
		
		}
		
		
		/**
		 * Método para identificar las personas registradas con correo de hotmail mediante una consulta
		 */
		
		
		
		@Test
		@Transactional(value=TransactionMode.ROLLBACK)
		@UsingDataSet({"persona.json","ciudad.json"})
		public void testListaPersonasConEmailHotmail () {
			
			
			TypedQuery<Persona> q = entityManager.createNamedQuery("LISTA_PERSONAS_USAN_CORREO_HOTMAIL", Persona.class);
			
			
			List<Persona>  l = q.getResultList();
			
			 for (Persona p : l) {
					
					System.out.println(p);
				}
		
		}
		

	
}
