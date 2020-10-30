package co.edu.uniquindio.unimotor.beans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.ejb.UnimotorEJBRemote;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Genero;
import co.edu.uniquindio.unimotor.entidades.Persona;
import co.edu.uniquindio.unimotor.entidades.Vendedor;

@Named
@ApplicationScoped
public class UsuarioBean {

	@EJB
	UnimotorEJBRemote unimotor;

	private Cliente cliente;

	private String cedula;

	private String nombre;

	private String email;

	private String clave;

	private String direccion;


	private String ciudad;

	private String genero;

	private String tipoPersona;


	public void registrar () {

		try {

			unimotor.registrarCliente(cliente);
			System.out.println(cedula);
			System.out.println(nombre);
			System.out.println(email);
			System.out.println(clave);
			System.out.println(direccion);
			System.out.println(ciudad);
			System.out.println(genero);

			Ciudad ciudad = new Ciudad();
			ciudad.setId(8);



			if (tipoPersona.equals("Cliente")) {

				Cliente clienteNuevo = new Cliente(Integer.parseInt(cedula), nombre, email, clave, direccion, ciudad);

				if (genero.equals("Masculino")) {
					clienteNuevo.setGenero(Genero.MASCULINO);
				}else {
					clienteNuevo.setGenero(Genero.FEMENINO);;
				}

				unimotor.registrarCliente(clienteNuevo);

				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Registro Exitoso!");
				FacesContext.getCurrentInstance().addMessage(null, msj);


			}else {
				Vendedor vendedor1= new Vendedor(Integer.parseInt(cedula), nombre, email, clave, direccion, ciudad);
				if (genero.equals("Masculino")) {
					vendedor1.setGenero(Genero.MASCULINO);;
				}else {
					vendedor1.setGenero(Genero.FEMENINO);;
				}


				unimotor.registrarVendedor(vendedor1);
				FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Registro Exitoso!");
				FacesContext.getCurrentInstance().addMessage(null, msj);

			}

		} catch (Exception e) {

			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
		}
	}

















	public Cliente getCliente() {
		return cliente;
	}

















	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

















	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getTipoPersona() {
		return tipoPersona;
	}


	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}






}
