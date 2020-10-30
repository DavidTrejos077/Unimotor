package co.edu.uniquindio.unimotor.dto;

import co.edu.uniquindio.unimotor.entidades.Vehiculo;

public class ConsultaVehiculoCaracteristicasDTO {

	private Vehiculo v;

	public ConsultaVehiculoCaracteristicasDTO(Vehiculo v) {
		super();
		this.v = v;
	}

	@Override
	public String toString() {
		return "ConsultaVehiculoCaracteristicasDTO [v=" + v + "]";
	}
	
	
}
