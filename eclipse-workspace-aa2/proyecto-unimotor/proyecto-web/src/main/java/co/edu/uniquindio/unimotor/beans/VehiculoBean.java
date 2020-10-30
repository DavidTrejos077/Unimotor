package co.edu.uniquindio.unimotor.beans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJBRemote;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.OpcionNuevoUsado;
import co.edu.uniquindio.unimotor.entidades.TipoCombustibleEnum;
import co.edu.uniquindio.unimotor.entidades.TipovehiculoEnum;
import co.edu.uniquindio.unimotor.entidades.Tranmision;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;

@Named
@ApplicationScoped
public class VehiculoBean {

	@EJB
	UnimotorEJBRemote unimotor;
	
	
	private String precio;
	
	private String placa;
	
	private String kilometraje;
	
	private String descripcion;
	
	private String color;
	
	private String anio;
	
	private String cilindraje;
	
	private int numeroPuertas;
	
	private String tipocombustible;
	
    private String carroNuevoUsado;

	private String tipovehiculo;
	
	
	private String ciudad;
	
	private String marca;
	
	
	private String modelo;
	
	private String nombrePublicacion;
	
	private String transmision;

	
	private UploadedFiles fotoVehiculos;
	
	private String []  caracteristica;

	public UnimotorEJBRemote getUnimotor() {
		return unimotor;
	}

	public void setUnimotor(UnimotorEJBRemote unimotor) {
		this.unimotor = unimotor;
	}
	
	

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	

	public String getNombrePublicacion() {
		return nombrePublicacion;
	}

	public void setNombrePublicacion(String nombrePublicacion) {
		this.nombrePublicacion = nombrePublicacion;
	}

	
	
	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public String getTipocombustible() {
		return tipocombustible;
	}

	public void setTipocombustible(String tipocombustible) {
		this.tipocombustible = tipocombustible;
	}

	public String getCarroNuevoUsado() {
		return carroNuevoUsado;
	}

	public void setCarroNuevoUsado(String carroNuevoUsado) {
		this.carroNuevoUsado = carroNuevoUsado;
	}

	public String getTipovehiculo() {
		return tipovehiculo;
	}

	public void setTipovehiculo(String tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	

	public String[] getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String[] caracteristica) {
		this.caracteristica = caracteristica;
	}

	public UploadedFiles getFotoVehiculos() {
		return fotoVehiculos;
	}

	public void setFotoVehiculos(UploadedFiles fotoVehiculos) {
		this.fotoVehiculos = fotoVehiculos;
	}
	
	public void uploadMultiple() {
        if (fotoVehiculos != null) {
            for (UploadedFile f : fotoVehiculos.getFiles()) {
                FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
	
    public void registrarVehiculo () {
    	
    	
    	
    	try {
    		Vehiculo vehiculoNuevo = new Vehiculo ();
        	vehiculoNuevo.setAnio(Integer.parseInt(anio));
        	if (carroNuevoUsado.equals("Nuevo")) {
        		vehiculoNuevo.setCarroNuevoUsado(OpcionNuevoUsado.NUEVO);
        	}else {
        		vehiculoNuevo.setCarroNuevoUsado(OpcionNuevoUsado.USADO);
        	}
        	
        	vehiculoNuevo.setCilindraje(Integer.parseInt(cilindraje));
        	Ciudad ciudad1 = new Ciudad(8, "Pitalito");
        	vehiculoNuevo.setNombrePublicacion(nombrePublicacion);
        	vehiculoNuevo.setCiudad(ciudad1);
        	vehiculoNuevo.setColor(color);
        	vehiculoNuevo.setDescripcion(descripcion);
        	vehiculoNuevo.setKilometraje(Long.parseLong(kilometraje));
        	Marca marca1 = new Marca(1, "Renault");
        	vehiculoNuevo.setMarca(marca1);
        	Modelo modelo1 = new Modelo (1, "Onix");
        	vehiculoNuevo.setModelo(modelo1);
        	vehiculoNuevo.setNumeropuertas(numeroPuertas);
        	vehiculoNuevo.setPlaca(placa);
        	vehiculoNuevo.setPrecio(Long.parseLong(precio));
        	
        	if (tipocombustible.equals("GAS")) {
        		vehiculoNuevo.setTipocombustible(TipoCombustibleEnum.GAS);
        	}else if (tipocombustible.equals("GASOLINA")){
        		vehiculoNuevo.setTipocombustible(TipoCombustibleEnum.GASOLINA);
        	}else {
        		vehiculoNuevo.setTipocombustible(TipoCombustibleEnum.DIESEL);
        	}
        	
        	if (tipovehiculo.equals("CARRO")) {
        		vehiculoNuevo.setTipovehiculo(TipovehiculoEnum.CARRO);
        	}else if (tipovehiculo.equals("CAMIONETA")){
        		vehiculoNuevo.setTipovehiculo(TipovehiculoEnum.CAMIONETA);
        	}else if (tipovehiculo.equals("CAMIONES")) {
        		vehiculoNuevo.setTipovehiculo(TipovehiculoEnum.CAMIONES);
        	}else {
        		vehiculoNuevo.setTipovehiculo(TipovehiculoEnum.CARROSDECOLECCION);
        	}
        	
        	if(transmision.equals("MECANICA")) {
        		vehiculoNuevo.setTransmision(Tranmision.MECANICA);
        	}else {
        		vehiculoNuevo.setTransmision(Tranmision.AUTOMATICA);
        	}
        	
        	unimotor.registrarVehiculo(vehiculoNuevo);
        	
        	FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Registro Exitoso!");
			FacesContext.getCurrentInstance().addMessage(null, msj);
			
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
		}
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
