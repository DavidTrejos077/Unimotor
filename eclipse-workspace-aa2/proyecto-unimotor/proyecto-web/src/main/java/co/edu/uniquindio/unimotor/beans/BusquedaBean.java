package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.unimotor.ejb.UnimotorEJB;
import co.edu.uniquindio.unimotor.entidades.Vehiculo;
@FacesConfig(version=Version.JSF_2_3)
@Named
@ViewScoped
public class BusquedaBean implements Serializable {
	
	@EJB
     private UnimotorEJB clienteEJB;
	 private String busqueda;
	 private List <Vehiculo> vehiculos;
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void inicializar () {
		if(busquedaParam!=null) {
			vehiculos = clienteEJB.buscarVehiculos(busquedaParam);
		}
	}
	
	
   
    
    @Inject
    @ManagedProperty(value="#{param.busqueda}")
    private String busquedaParam;
    
    public String buscar (){
    	
    	
    	
    	return "resultadoBusqueda?faces-redirect=true&amp;busqueda=" + busqueda;
    }
    
    

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	
	
}
