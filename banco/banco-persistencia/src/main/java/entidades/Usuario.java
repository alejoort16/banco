package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="Usuario.buscarXUser",query="SELECT u FROM Usuario u WHERE u.user=?1")
})
@Table(name="Usuario")
@Entity
public class Usuario implements Serializable {
	
	@Id
	@Column(unique=true,name="usuario")
    private String user;
	
	@Column(name="contra")
	private String password;
	
	
	
	
	@JoinColumns({

		@JoinColumn(name = "identificationType", referencedColumnName = "identification_type"),
		@JoinColumn(name = "identificationNumber", referencedColumnName = "identification_number") 
		})
	@OneToOne
	private Cliente cliente;
	
	public Usuario() {
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
