package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumeraciones.TipoDocumento;

@NamedQueries({
@NamedQuery(name=Cliente.listar, query="select f from Cliente f")
})
@Entity
@Table(name="T_CLIENTES")
@IdClass(ClientePK.class)
public class Cliente implements Serializable  {
	
	public static final String listar = "listarCliente";
	
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@Column(name="lastname", nullable= false, length=50)
	private String lastName;
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name="identification_type", nullable= false, length=10)
	private TipoDocumento identificationType;
	
	@Id
	@Column(name="identification_number", nullable= false, length=15)
	private String identificationNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy= "cliente")
	private List<Producto> productos;
	public Cliente(){
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificationNumber == null) ? 0 : identificationNumber.hashCode());
		result = prime * result + ((identificationType == null) ? 0 : identificationType.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productos == null) ? 0 : productos.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (identificationNumber == null) {
			if (other.identificationNumber != null)
				return false;
		} else if (!identificationNumber.equals(other.identificationNumber))
			return false;
		if (identificationType == null) {
			if (other.identificationType != null)
				return false;
		} else if (!identificationType.equals(other.identificationType))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		return true;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the identificationType
	 */
	public TipoDocumento getIdentificationType() {
		return identificationType;
	}
	/**
	 * @param identificationType the identificationType to set
	 */
	public void setIdentificationType(TipoDocumento identificationType) {
		this.identificationType = identificationType;
	}
	/**
	 * @return the identificationNumber
	 */
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	/**
	 * @param identificationNumber the identificationNumber to set
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}
	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Cliente(String name, String lastName, TipoDocumento identificationType, String identificationNumber,
			List<Producto> productos) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.identificationType = identificationType;
		this.identificationNumber = identificationNumber;
		this.productos = productos;
	}
	
	public Cliente(String name, String lastName, TipoDocumento identificationType, String identificationNumber) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.identificationType = identificationType;
		this.identificationNumber = identificationNumber;
	}
	
	
	
	
	

}
