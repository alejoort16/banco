package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
		@NamedQuery(name = ConsumoTarjetaCredito.finByTc, query = "select f from ConsumoTarjetaCredito f where f.isPayed = false and "
				+ "f.creditCardNumber =?1"),
		@NamedQuery(name = ConsumoTarjetaCredito.finByTcyWeb, query = "select f from ConsumoTarjetaCredito"
				+ " f join f.creditCardNumber tc  where f.isPayed = false and "
				+ "f.creditCardNumber =?1 and tc.cliente.identificationNumber=?2"),
		@NamedQuery(name = ConsumoTarjetaCredito.sumaRemainin, query = "SELECT SUM(c.remainingAmmount)FROM ConsumoTarjetaCredito c where c.creditCardNumber = ?1") })
@Entity
@Table(name = "T_CREDITCARD_CONSUME")
public class ConsumoTarjetaCredito implements Serializable {

	public static final String sumaRemainin = "sumatoriaRemainin";
	public static final String finByTc = "listarConsumos";
	public static final String finByTcyWeb = "listarConsumosParaWeb";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_consume", nullable = false)
	private Date dateConsume;

	@Column(name = "number_shares", nullable = false)
	private int numberShares;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Column(name = "interest", nullable = false)
	private double interest;

	@Column(name = "remaining_ammount", nullable = false)
	private double remainingAmmount;

	@Column(name = "is_payed", nullable = false)
	private boolean isPayed;

	@JoinColumn(name = "creditcard_number")
	@ManyToOne
	private TarjetaCredito creditCardNumber;

	@Column(name = "cuotas_restantes")
	private int cuotasRestantes;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "idConsume")
	private List<PagoCunsumoTarCredito> pago;

	public ConsumoTarjetaCredito() {

	}

	public ConsumoTarjetaCredito(int id, Date dateConsume, int numberShares, double ammount, double interest,
			double remainingAmmount, boolean isPayed, TarjetaCredito creditCardNumber, int cuotRes) {
		super();
		this.id = id;
		this.dateConsume = dateConsume;
		this.numberShares = numberShares;
		this.ammount = ammount;
		this.interest = interest;
		this.remainingAmmount = remainingAmmount;
		this.isPayed = isPayed;
		this.creditCardNumber = creditCardNumber;
		this.cuotasRestantes = cuotRes;
	}

	/**
	 * @return the cuotasRestantes
	 */
	public int getCuotasRestantes() {
		return cuotasRestantes;
	}

	/**
	 * @param cuotasRestantes
	 *            the cuotasRestantes to set
	 */
	public void setCuotasRestantes(int cuotasRestantes) {
		this.cuotasRestantes = cuotasRestantes;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dateConsume
	 */
	public Date getDateConsume() {
		return dateConsume;
	}

	/**
	 * @param dateConsume
	 *            the dateConsume to set
	 */
	public void setDateConsume(Date dateConsume) {
		this.dateConsume = dateConsume;
	}

	/**
	 * @return the numberShares
	 */
	public int getNumberShares() {
		return numberShares;
	}

	/**
	 * @param numberShares
	 *            the numberShares to set
	 */
	public void setNumberShares(int numberShares) {
		this.numberShares = numberShares;
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
	 * @return the interest
	 */
	public double getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            the interest to set
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}

	/**
	 * @return the remainingAmmount
	 */
	public double getRemainingAmmount() {
		return remainingAmmount;
	}

	/**
	 * @param remainingAmmount
	 *            the remainingAmmount to set
	 */
	public void setRemainingAmmount(double remainingAmmount) {
		this.remainingAmmount = remainingAmmount;
	}

	/**
	 * @return the isPayed
	 */
	public boolean isPayed() {
		return isPayed;
	}

	/**
	 * @param isPayed
	 *            the isPayed to set
	 */
	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	/**
	 * @return the creditCardNumber
	 */
	public TarjetaCredito getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @param creditCardNumber
	 *            the creditCardNumber to set
	 */
	public void setCreditCardNumber(TarjetaCredito creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @return the pago
	 */
	public List<PagoCunsumoTarCredito> getPago() {
		return pago;
	}

	/**
	 * @param pago
	 *            the pago to set
	 */
	public void setPago(List<PagoCunsumoTarCredito> pago) {
		this.pago = pago;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((dateConsume == null) ? 0 : dateConsume.hashCode());
		result = prime * result + id;
		temp = Double.doubleToLongBits(interest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (isPayed ? 1231 : 1237);
		result = prime * result + numberShares;
		result = prime * result + ((pago == null) ? 0 : pago.hashCode());
		temp = Double.doubleToLongBits(remainingAmmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumoTarjetaCredito other = (ConsumoTarjetaCredito) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (dateConsume == null) {
			if (other.dateConsume != null)
				return false;
		} else if (!dateConsume.equals(other.dateConsume))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(interest) != Double.doubleToLongBits(other.interest))
			return false;
		if (isPayed != other.isPayed)
			return false;
		if (numberShares != other.numberShares)
			return false;
		if (pago == null) {
			if (other.pago != null)
				return false;
		} else if (!pago.equals(other.pago))
			return false;
		if (Double.doubleToLongBits(remainingAmmount) != Double.doubleToLongBits(other.remainingAmmount))
			return false;
		return true;
	}

}
