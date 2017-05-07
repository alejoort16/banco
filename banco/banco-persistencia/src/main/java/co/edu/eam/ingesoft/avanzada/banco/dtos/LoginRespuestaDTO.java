package co.edu.eam.ingesoft.avanzada.banco.dtos;

public class LoginRespuestaDTO {

	
	private String token;
	private String identificacion;
	
	public LoginRespuestaDTO(){
		
	}

	
	public LoginRespuestaDTO(String token, String identificacion) {
		super();
		this.token = token;
		this.identificacion = identificacion;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	
}
