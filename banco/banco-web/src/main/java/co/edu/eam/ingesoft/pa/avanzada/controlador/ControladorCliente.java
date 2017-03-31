package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.Cliente;
import enumeraciones.TipoDocumento;

@Named("ControladorClientes")
@ViewScoped
public class ControladorCliente implements Serializable{

	/**
	 * tipo de documento a seleccionar
	 */
	private TipoDocumento tipoSeleccionado;
	
	@Pattern(regexp="[0-9]*",message="solo numeros")
	@Length(min=4,max=15,message="longitud entre 4 y 15")
	private String numeroIdentificacion;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 50")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="solo Letras")
	@Length(min=3,max=50,message="longitud entre 3 y 50")
	private String apellido;
	
	@Email
	private String correo;
	
	@Pattern(regexp="[0-9]*",message="solo numeros")
	@Length(min=10,max=13,message="longitud entre 10 y 13")
	private String celular;
	
	private List<Cliente> clientes;
	

	@EJB
	private ClienteEJB clienteEJB;
	
	@PostConstruct
	public void inicializar(){
		clientes = clienteEJB.listar();
	}
	
	public TipoDocumento[] getTipos() {
		return TipoDocumento.values();
	}
	
	/**
	 * metodo para crear un cliente, recibe los atributos de arriba, pero ya asignados valor
	 * desde la pagina clientes
	 */
	public void crear(){
		try{
		Cliente cli = new Cliente(nombre, apellido,celular,correo,tipoSeleccionado, numeroIdentificacion);
				clienteEJB.crearCliente(cli);
				
				// limpiar campos
				nombre ="";
				apellido  ="";
				numeroIdentificacion ="";
				celular ="";
				correo="";
				clientes = clienteEJB.listar();	
				
				Messages.addFlashGlobalInfo("Cliente creado");
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * metodo para buscar un cliente por tipo y numero de identificacion
	 */
	public void buscar(){
		Cliente cli = clienteEJB.buscarCliente(tipoSeleccionado, numeroIdentificacion);
		if(cli!=null){
			nombre = cli.getName();
			apellido = cli.getLastName();
			Messages.addFlashGlobalInfo("Cliente encontrado");
			
		}else{
			Messages.addFlashGlobalError("Cliente no existe");
	 	}
	}
	
	/**
	 * @return the tipoSeleccionado
	 */
	public TipoDocumento getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	/**
	 * @param tipoSeleccionado the tipoSeleccionado to set
	 */
	public void setTipoSeleccionado(TipoDocumento tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	/**
	 * @return the numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * @param numeroIdentificacion the numeroIdentificacion to set
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the clienteEJB
	 */
	public ClienteEJB getClienteEJB() {
		return clienteEJB;
	}

	/**
	 * @param clienteEJB the clienteEJB to set
	 */
	public void setClienteEJB(ClienteEJB clienteEJB) {
		this.clienteEJB = clienteEJB;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	

}
