package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
@NamedQuery(name=TarjetaCredito.contarCantidadTCCliente, query="SELECT count(t) FROM TarjetaCredito"
		+ " t WHERE t.cliente=?1"),	//recibe cliente!!!!
@NamedQuery(name=TarjetaCredito.tarjetasCliente, query="SELECT t FROM TarjetaCredito t "
		+ "WHERE t.cliente=?1")
})
@Entity
@Table(name = "T_CREDITCARD")
public class TarjetaCredito extends Producto implements Serializable {
	
	public static final String contarCantidadTCCliente = "contarCantidaddeCliente";
	public static final String tarjetasCliente = "tarjetasCliente";

	@Column(name = "cvc", nullable = false, length = 3)
	private String cvc;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	@JoinColumn(name = "franchise")
	@ManyToOne
	private Franquicia franchise;

	public TarjetaCredito() {

	}

	public TarjetaCredito(String cvc, double ammount, Date expirationDate, Franquicia franchise) {
		super();
		this.cvc = cvc;
		this.ammount = ammount;
		this.expirationDate = expirationDate;
		this.franchise = franchise;
	}

	public TarjetaCredito(String number, Date expedition_date, Cliente cliente, String cvc, double ammount,
			Date expirationDate, Franquicia franchise) {
		super(number, expedition_date, cliente);
		this.cvc = cvc;
		this.ammount = ammount;
		this.expirationDate = expirationDate;
		this.franchise = franchise;
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @return the cvc
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * @param cvc
	 *            the cvc to set
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}

	/**
	 * @param ammount
	 *            the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the franchise
	 */
	public Franquicia getFranchise() {
		return franchise;
	}

	/**
	 * @param franchise
	 *            the franchise to set
	 */
	public void setFranchise(Franquicia franchise) {
		this.franchise = franchise;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((franchise == null) ? 0 : franchise.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarjetaCredito other = (TarjetaCredito) obj;
		if (cvc == null) {
			if (other.cvc != null)
				return false;
		} else if (!cvc.equals(other.cvc))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (franchise == null) {
			if (other.franchise != null)
				return false;
		} else if (!franchise.equals(other.franchise))
			return false;
		return true;
	}

}
