package co.edu.uniquindio.unimotor.dto;



import co.edu.uniquindio.unimotor.entidades.Vehiculo;



public class ConsultaVehiculosPorCadaMarcaDTO {
	
	

	private int id;
	private String nombreMarca;
	private Long cantidadVehiculos;
	
	
	
	public ConsultaVehiculosPorCadaMarcaDTO(int id, String nombreMarca, Long cantidadVehiculos) {
		super();
		this.id = id;
		this.nombreMarca = nombreMarca;
		this.cantidadVehiculos = cantidadVehiculos;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombreMarca() {
		return nombreMarca;
	}



	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}



	public Long getCantidadVehiculos() {
		return cantidadVehiculos;
	}



	public void setCantidadVehiculos(Long cantidadVehiculos) {
		this.cantidadVehiculos = cantidadVehiculos;
	}



	@Override
	public String toString() {
		return "ConsultaVehiculosPorCadaMarcaDTO [id=" + id + ", nombreMarca=" + nombreMarca + ", cantidadVehiculos="
				+ cantidadVehiculos + "]";
	}
	
	

	
	
	

}
