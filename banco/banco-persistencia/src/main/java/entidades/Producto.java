package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries({
@NamedQuery(name=Producto.contarCantidadProcli, query="SELECT count(p) FROM Producto p WHERE p.cliente=?1")	//recibe cliente!!!!
})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_PRODUCT")
public class Producto implements Serializable {
	
	public static final String contarCantidadProcli = "contarCantidaddeClientePro";

	@Id
	@Column(name = "number", nullable = false)
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "expedition_date", nullable = false)
	protected Date expedition_date;

	@JoinColumns({

			@JoinColumn(name = "identificationType", referencedColumnName = "identification_type"),
			@JoinColumn(name = "identificationNumber", referencedColumnName = "identification_number") 
			})
	@ManyToOne
	protected Cliente cliente;	
	
	
	

	public Producto(String number, Date expedition_date, Cliente cliente) {
		super();
		this.number = number;
		this.expedition_date = expedition_date;
		this.cliente = cliente;
	}



	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}



	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}



	/**
	 * @return the expedition_date
	 */
	public Date getExpedition_date() {
		return expedition_date;
	}



	/**
	 * @param expedition_date the expedition_date to set
	 */
	public void setExpedition_date(Date expedition_date) {
		this.expedition_date = expedition_date;
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



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((expedition_date == null) ? 0 : expedition_date.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Producto other = (Producto) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (expedition_date == null) {
			if (other.expedition_date != null)
				return false;
		} else if (!expedition_date.equals(other.expedition_date))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}



	public Producto(){	
	}
	
	
}


