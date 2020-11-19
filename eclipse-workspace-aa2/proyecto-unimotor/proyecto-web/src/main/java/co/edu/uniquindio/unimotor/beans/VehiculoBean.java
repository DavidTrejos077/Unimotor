package co.edu.uniquindio.unimotor.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;


import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Caracteristica;
import co.edu.uniquindio.unimotor.entidades.Ciudad;
import co.edu.uniquindio.unimotor.entidades.Cliente;
import co.edu.uniquindio.unimotor.entidades.Marca;
import co.edu.uniquindio.unimotor.entidades.Modelo;
import co.edu.uniquindio.unimotor.entidades.OpcionNuevoUsado;
import co.edu.uniquindio.unimotor.entidades.TipoCombustibleEnum;
import co.edu.uniquindio.unimotor.entidades.TipovehiculoEnum;
import co.edu.uniquindio.unimotor.entidades.Tranmision;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;


@Named
@ViewScoped
public class VehiculoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EJB
	private UnimotorEJB unimotor;
	
	private List<SelectItem> listaModelos = new ArrayList<SelectItem>();
	
	private List<SelectItem> listaMarcas = new ArrayList<SelectItem>();
	
	private List<SelectItem> listaCaracteristicas = new ArrayList<SelectItem>();
	
	private List<SelectItem> listaCiudades = new ArrayList<SelectItem>();
	
	private ArrayList <String> imagenes;
	
	private static final String RUTA_FOTOS ="C:\\Users\\PC\\payara5\\glassfish\\domains\\domain1\\docroot\\Unimotor\\";
	
	private Vehiculo vehiculo;
	
	private String tipocombustible;
	
    private String carroNuevoUsado;

	private String tipovehiculo;
	
	private String transmision;
	
	private UploadedFiles fotoVehiculos;
	
	
	
	private String []  caracteristica;

	@PostConstruct
    public void init() {
		System.out.println("init Vehiculo");
		Ciudad ciudadVehiculo = new Ciudad();		
		Marca marcaVehiculo = new Marca();		
		Modelo modeloVehiculo = new Modelo();
		imagenes = new ArrayList <String>();
		vehiculo = new Vehiculo();
		
		vehiculo.setCiudad(ciudadVehiculo);
		vehiculo.setMarca(marcaVehiculo);
		vehiculo.setModelo(modeloVehiculo);
		List<Ciudad> listaCiudadesDB =  unimotor.obtenerListaCiudades();
		for(Ciudad temp: listaCiudadesDB) {
			SelectItem select = new SelectItem();
			select.setLabel(temp.getNombre());
			select.setValue(String.valueOf(temp.getId()));
			listaCiudades.add(select);
		}
		
		List<Marca> listaMarcasDB = unimotor.obtenerListaMarcas();
		for(Marca temp: listaMarcasDB) {
			SelectItem select = new SelectItem();
			select.setLabel(temp.getNombre());
			select.setValue(String.valueOf(temp.getId()));
			listaMarcas.add(select);
		}
		List<Caracteristica> listaCaracteristicasDB = unimotor.obtenerListaCaracteristicas();
		for(Caracteristica temp: listaCaracteristicasDB) {
			SelectItem select = new SelectItem();
			select.setLabel(temp.getNombre());
			select.setValue(String.valueOf(temp.getId()));
			listaCaracteristicas.add(select);
		}
		 
	}


	public void buscarModelosPorMarca(ValueChangeEvent event) {
		int idMarca = (Integer) event.getNewValue();
		Marca marcaSelecccionada = new Marca();
		marcaSelecccionada.setId(idMarca);
		listaModelos = new ArrayList<SelectItem>();
		List<Modelo> listaModelosDB = unimotor.obtenerListaModelosPorMarca(marcaSelecccionada); 
		for(Modelo temp: listaModelosDB) {
			SelectItem select = new SelectItem();
			select.setLabel(temp.getNombre());
			select.setValue(String.valueOf(temp.getId()));
			listaModelos.add(select);
		}
	}
	
	public void subirImagenes (FileUploadEvent event) {
		UploadedFile imagen = event.getFile();
		String nombreImagen = subirImagen(imagen);
		if (nombreImagen!=null) {
			imagenes.add(nombreImagen);
		}
	}
	
	public String subirImagen (UploadedFile file) {
		
		try {
			InputStream input = file.getInputStream();
			String filename = FilenameUtils.getName(file.getFileName());
			String basename=FilenameUtils.getBaseName(filename)+ "_";
			String extension= "."+ FilenameUtils.getExtension(filename);
			
			File fileDest = File.createTempFile(basename, extension, new File(RUTA_FOTOS));
			FileOutputStream output = new FileOutputStream(fileDest);
			
			IOUtils.copy(input, output);
			return fileDest.getName();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
    public void registrarVehiculo () {
    	try {
    		
    		if(!imagenes.isEmpty()) {
    			
    		
    			vehiculo.setFotoVehiculos(imagenes);
    		
    		System.out.println(vehiculo);
        	if (carroNuevoUsado.equals("Nuevo")) {
        		vehiculo.setCarroNuevoUsado(OpcionNuevoUsado.NUEVO);
        	}else {
        		vehiculo.setCarroNuevoUsado(OpcionNuevoUsado.USADO);
        	}
        	Cliente persona1 = unimotor.obtenerPorEmail("juanstc@gmail.com");
        	vehiculo.setPersona(persona1);
        	
        	if (tipocombustible.equals("GAS")) {
        		vehiculo.setTipocombustible(TipoCombustibleEnum.GAS);
        	}else if (tipocombustible.equals("GASOLINA")){
        		vehiculo.setTipocombustible(TipoCombustibleEnum.GASOLINA);
        	}else {
        		vehiculo.setTipocombustible(TipoCombustibleEnum.DIESEL);
        	}
        	
        	if (tipovehiculo.equals("CARRO")) {
        		vehiculo.setTipovehiculo(TipovehiculoEnum.CARRO);
        	}else if (tipovehiculo.equals("CAMIONETA")){
        		vehiculo.setTipovehiculo(TipovehiculoEnum.CAMIONETA);
        	}else if (tipovehiculo.equals("CAMIONES")) {
        		vehiculo.setTipovehiculo(TipovehiculoEnum.CAMIONES);
        	}else {
        		vehiculo.setTipovehiculo(TipovehiculoEnum.CARROSDECOLECCION);
        	}
        	
        	if(transmision.equals("MECANICA")) {
        		vehiculo.setTransmision(Tranmision.MECANICA);
        	}else {
        		vehiculo.setTransmision(Tranmision.AUTOMATICA);
        	}
        	
        	unimotor.registrarVehiculo(vehiculo);
        	
        	FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Registro Exitoso!");
			FacesContext.getCurrentInstance().addMessage(null, msj);
    	}else {
    		FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario asignar imagen al vehiculo");
			FacesContext.getCurrentInstance().addMessage(null, msj);
    	}
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msj);
			e.printStackTrace();
		}
    }
    
   


	public List<SelectItem> getListaModelos() {
		return listaModelos;
	}


	public void setListaModelos(List<SelectItem> listaModelos) {
		this.listaModelos = listaModelos;
	}


	public List<SelectItem> getListaMarcas() {
		return listaMarcas;
	}


	public void setListaMarcas(List<SelectItem> listaMarcas) {
		this.listaMarcas = listaMarcas;
	}


	public List<SelectItem> getListaCaracteristicas() {
		return listaCaracteristicas;
	}


	public void setListaCaracteristicas(List<SelectItem> listaCaracteristicas) {
		this.listaCaracteristicas = listaCaracteristicas;
	}


	public List<SelectItem> getListaCiudades() {
		return listaCiudades;
	}


	public void setListaCiudades(List<SelectItem> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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


	public String getTransmision() {
		return transmision;
	}


	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}


	public UploadedFiles getFotoVehiculos() {
		return fotoVehiculos;
	}


	public void setFotoVehiculos(UploadedFiles fotoVehiculos) {
		this.fotoVehiculos = fotoVehiculos;
	}


	public String[] getCaracteristica() {
		return caracteristica;
	}


	public void setCaracteristica(String[] caracteristica) {
		this.caracteristica = caracteristica;
	}
    
    
    

}
