package co.edu.eam.ingesoft.avanzada.banco.dtos;

public class AsociarCuentaDTO {

	
	private String numeroCuenta;
	
	private String nombreAsoc;
	 
	private String nombreTit;
    
	private int tipoDoc;
    
	private String cedula;
    
	private String estado;
    
	private String codigoBanco;
    
	private String cedCliente;
    
	private int tipo;

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

	public int getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(int tipoDoc) {
		this.tipoDoc = tipoDoc;
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

	public String getCedCliente() {
		return cedCliente;
	}

	public void setCedCliente(String cedCliente) {
		this.cedCliente = cedCliente;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
    
    
    
    
	
}
