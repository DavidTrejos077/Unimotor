package co.edu.uniquindio.unimotor.beans;
/**
 * Clase para dar acceso al usuario a la plataforma de unimotor con todas sus funcionalidades.
 */

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.RandomStringUtils;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Persona;

@Named
@SessionScoped
public class SeguridadBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UnimotorEJB unimotorEJB;
	
	private Persona persona;
	private boolean autenticado;
	
	@Email
	private String emailLogin;
	
	private String passwordLogin;
	
//	private String host;
//	
//	private String port;
//	
//	private String remitente;
//	
//	private String name;
//	
//	private String pass;
	
	@PostConstruct
	public void inicializar () {
		autenticado=false;
		persona = new Persona();
//		ServletContext servletContext = (ServletContext) FacesContext
//		        .getCurrentInstance().getExternalContext().getContext();
//		host = servletContext.getInitParameter("host");
//		port = servletContext.getInitParameter("port");
//		remitente = servletContext.getInitParameter("email");
//		name = servletContext.getInitParameter("name");
//		pass = servletContext.getInitParameter("pass");
	}
	
	/**
	 * Método para auntenticar usuario.
	 */

	
	public String autenticarUsuario () {
		try {
			Persona p = unimotorEJB.iniciarSesion(emailLogin,passwordLogin);
			
			if (p!=null) {
				
				autenticado = true;
				persona = p;
				return "/index?faces-redirect=true";
			}
		}catch (Exception e) {
			FacesMessage m= new FacesMessage (FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
		    FacesContext.getCurrentInstance().addMessage("mensaje-sesion", m);
		}
		return null;
	}
	 
	/**
	 * Método para enviar correo.
	 */

	
	public String enviarCorreo () {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/indexRecuperarPassword?faces-redirect=true";
	}
	
//	public String recuperarPassword () {
//		try {
//			
//			Persona persona1 = unimotorEJB.buscarPersona(emailLogin);
//			if(persona1!= null) {
//				String newPassword = resetCustomerPassword(emailLogin);
//				System.out.println("newPassword="+newPassword);
//				persona1.setClave(newPassword);
//				unimotorEJB.modificarPersona(persona1);
//				
//				String subject = "Se ha creado un nuevo password";
//				
//				String content = "Hola, este es su nuevo Password: " + newPassword;
//				
//				EmailUtility.sendEmail(host, port, remitente, name, pass,
//						emailLogin, subject, content);
//				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El nuevo password ha sido enviado al correo.");
//				FacesContext.getCurrentInstance().addMessage(null, msj);
//				
//			}
//			
//		} catch (Exception e) {
//			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
//			FacesContext.getCurrentInstance().addMessage(null, msj);
//			e.printStackTrace();
//			return null;
//		}
//		
//		return null;
//	}
//	
//	public String resetCustomerPassword(String email) {
//	     
//	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);
//	      
//	    return randomPassword;
//	}
	
	/**
	 * Método para cerrar sesión.
	 */

	public String cerrarSesion () {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/index?faces-redirect=true";
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

//	public String getHost() {
//		return host;
//	}
//
//	public void setHost(String host) {
//		this.host = host;
//	}
//
//	public String getPort() {
//		return port;
//	}
//
//	public void setPort(String port) {
//		this.port = port;
//	}
//
//	public String getRemitente() {
//		return remitente;
//	}
//
//	public void setRemitente(String remitente) {
//		this.remitente = remitente;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPass() {
//		return pass;
//	}
//
//	public void setPass(String pass) {
//		this.pass = pass;
//	}
	
	
}
