package co.edu.uniquindio.unimotor.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.Modelo;
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
     * Constructor por defecto de la clase Singleton. 
     */
    public SetupEJB() {
        
    }

    /**
     * Método para inicializar el Singleton.
     */
    
    @PostConstruct
    public void inicializar() {
    	TypedQuery<Long> q = entityManager.createNamedQuery("TODAS_PERSONAS", Long.class);
    	
    	if(q.getSingleResult() == 0) {
        	
    		
    		Ciudad c = new Ciudad("Armenia");
    		entityManager.persist(c);
    		
    		Persona c1 = new Cliente(12345, "Pepito", "pepe@email.com", "123", "Calle 123", c);
    		entityManager.persist(c1);    		
    		
    		Marca marca = new Marca("Chevrolet");
    		entityManager.persist(marca);
    		
    		Modelo md = new Modelo("Spark GT", marca); 
    		entityManager.persist(md);
    	}
    	
    	
    }
    
}
