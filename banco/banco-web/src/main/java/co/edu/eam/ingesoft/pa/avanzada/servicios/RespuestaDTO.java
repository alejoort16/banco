package co.edu.eam.ingesoft.pa.avanzada.servicios;

public class RespuestaDTO {
	
	
	private String mensaje;
	
	private int codigo;
	
	private Object obj;

	public RespuestaDTO(String mensaje, int codigo, Object obj) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.obj = obj;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	

}
