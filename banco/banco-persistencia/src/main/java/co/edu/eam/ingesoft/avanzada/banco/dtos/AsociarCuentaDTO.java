package co.edu.eam.ingesoft.avanzada.banco.dtos;

public class AsociarCuentaDTO {

	
	private String nombreTit;
	
	private int tipoDocTitular;
	
	private String cedula;
	
	private String numeroCuenta;
	
	private String nombreAsoc;
	     
	private String estado;
    
	private String codigoBanco;
    

    public AsociarCuentaDTO(){
    	
    }
    
    
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNombreAsoc() {
		return nombreAsoc;
	}

	public void setNombreAsoc(String nombreAsoc) {
		this.nombreAsoc = nombreAsoc;
	}

	public String getNombreTit() {
		return nombreTit;
	}

	public void setNombreTit(String nombreTit) {
		this.nombreTit = nombreTit;
	}


	public int getTipoDocTitular() {
		return tipoDocTitular;
	}


	public void setTipoDocTitular(int tipoDocTitular) {
		this.tipoDocTitular = tipoDocTitular;
	}


	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	
}
