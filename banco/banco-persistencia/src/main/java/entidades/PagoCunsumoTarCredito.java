package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_CREDITCARD_PAYMENT_CONSUME")
public class PagoCunsumoTarCredito implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable= false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date",nullable = false)
	private Date paymentDate;
	
	@ManyToOne
	@JoinColumn(name="id_consume")
	private ConsumoTarjetaCredito idConsume;
	
	@Column(name="share",nullable= false)
	private int share;
	
	@Column(name="ammount", nullable = false)
	private double ammount;
	
	@Column(name= "capital_ammount", nullable= false)
	private double capitalAmmount;
	
	@Column(name= "interest_ammount", nullable= false)
	private double interestAmmount;
	
	public PagoCunsumoTarCredito(){
		
	}

	
	

	public PagoCunsumoTarCredito(int id, Date paymentDate, ConsumoTarjetaCredito idConsume, int share, double ammount,
			double capitalAmmount, double interestAmmount) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.idConsume = idConsume;
		this.share = share;
		this.ammount = ammount;
		this.capitalAmmount = capitalAmmount;
		this.interestAmmount = interestAmmount;
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}



	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	/**
	 * @return the idConsume
	 */
	public ConsumoTarjetaCredito getIdConsume() {
		return idConsume;
	}



	/**
	 * @param idConsume the idConsume to set
	 */
	public void setIdConsume(ConsumoTarjetaCredito idConsume) {
		this.idConsume = idConsume;
	}



	/**
	 * @return the share
	 */
	public int getShare() {
		return share;
	}



	/**
	 * @param share the share to set
	 */
	public void setShare(int share) {
		this.share = share;
	}



	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}



	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}



	/**
	 * @return the capitalAmmount
	 */
	public double getCapitalAmmount() {
		return capitalAmmount;
	}



	/**
	 * @param capitalAmmount the capitalAmmount to set
	 */
	public void setCapitalAmmount(double capitalAmmount) {
		this.capitalAmmount = capitalAmmount;
	}



	/**
	 * @return the interestAmmount
	 */
	public double getInterestAmmount() {
		return interestAmmount;
	}



	/**
	 * @param interestAmmount the interestAmmount to set
	 */
	public void setInterestAmmount(double interestAmmount) {
		this.interestAmmount = interestAmmount;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(capitalAmmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((idConsume == null) ? 0 : idConsume.hashCode());
		temp = Double.doubleToLongBits(interestAmmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + share;
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
		PagoCunsumoTarCredito other = (PagoCunsumoTarCredito) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (Double.doubleToLongBits(capitalAmmount) != Double.doubleToLongBits(other.capitalAmmount))
			return false;
		if (id != other.id)
			return false;
		if (idConsume == null) {
			if (other.idConsume != null)
				return false;
		} else if (!idConsume.equals(other.idConsume))
			return false;
		if (Double.doubleToLongBits(interestAmmount) != Double.doubleToLongBits(other.interestAmmount))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		if (share != other.share)
			return false;
		return true;
	}
	
	
}
