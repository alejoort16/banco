package co.edu.eam.ingesoft.avanzada.banco.dtos;

public class TransferirDTO {

	
	public String numeroCuenta;
	
	public double cantidad;

	
	public TransferirDTO(){
		
	}
	
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
