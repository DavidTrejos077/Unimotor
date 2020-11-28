package co.edu.uniquindio.unimotor.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.RandomStringUtils;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Persona;
/**
 * Clase para desarrollar componentes del login.
 */

@Named
@ApplicationScoped
public class LoginBean {

	@EJB
	private UnimotorEJB unimotor;

	private Persona persona;
	
	private Persona personaRecuperar;
	
	private String host;
	
	private String port;
	
	private String remitente;
	
	private String name;
	
	private String pass;
	
	@PostConstruct
    public void init() {
		persona = new Persona();
		personaRecuperar = new Persona();
		System.out.println("init");
		ServletContext servletContext = (ServletContext) FacesContext
		        .getCurrentInstance().getExternalContext().getContext();
		host = servletContext.getInitParameter("host");
		port = servletContext.getInitParameter("port");
		remitente = servletContext.getInitParameter("email");
		name = servletContext.getInitParameter("name");
		pass = servletContext.getInitParameter("pass");
		
	}
	 
	/**
	 * Método para iniciar sesión dado un email y una clave.
	 */

	
	public String iniciarSesion () {
		try {
			persona = unimotor.iniciarSesion(persona.getEmail(), persona.getClave());
			if(persona != null) {
				return "registroVehiculo";
			}else {
				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Usuario y/o Password Invalidos");
				FacesContext.getCurrentInstance().addMessage(null, msj);
				return null;
			}
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Método para recuperar una contraseña dado un correo. 
	 */


	public String recuperarPassword () {
		try {
			
			Persona persona1 = unimotor.buscarPersona(personaRecuperar.getEmail());
			if(persona1!= null) {
				String newPassword = resetCustomerPassword(personaRecuperar.getEmail());
				System.out.println("newPassword="+newPassword);
				persona1.setClave(newPassword);
				unimotor.modificarPersona(persona1);
				
				String subject = "Se ha creado un nuevo password";
				
				String content = "Hola, este es su nuevo Password: " + newPassword;
				
				EmailUtility.sendEmail(host, port, remitente, name, pass,
						personaRecuperar.getEmail(), subject, content);
				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El nuevo password ha sido enviado al correo.");
				FacesContext.getCurrentInstance().addMessage(null, msj);
				
			}
			
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	/**
	 * Método para recetear contraseña.
	 */

	
	public String resetCustomerPassword(String email) {
	     
	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);
	      
	    return randomPassword;
	}

	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDestinatario() {
		return remitente;
	}

	public void setDestinatario(String destinatario) {
		this.remitente = destinatario;
	}

	public Persona getPersonaRecuperar() {
		return personaRecuperar;
	}

	public void setPersonaRecuperar(Persona personaRecuperar) {
		this.personaRecuperar = personaRecuperar;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}


	
	
}
