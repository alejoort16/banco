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
@Table(name="T_TRANSACTION")
public class Transaccion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable= false)
	private int id;
	
	@Column(name="ammount", nullable= false)
	private double ammount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="transaction_date", nullable = false)
	private Date transactionDate;
	
	
	@Column(name="source_transaction", nullable= false)
	private String sourceTransaction;
	
	@Column(name="numero_verificacion")
	private int numeroVerificacion;
	
	@JoinColumn(name="saving_account_number")
	@ManyToOne
	private CuentaAhorros savingAccountNumber;
	
	public Transaccion(){
		
	}
	
	public Transaccion(int id, double ammount, Date transactionDate, String sourceTransaction,
			CuentaAhorros savingAccountNumber, String type) {
		super();
		this.id = id;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.sourceTransaction = sourceTransaction;
		this.savingAccountNumber = savingAccountNumber;
		this.type = type;
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
		result = prime * result + id;
		result = prime * result + ((savingAccountNumber == null) ? 0 : savingAccountNumber.hashCode());
		result = prime * result + ((sourceTransaction == null) ? 0 : sourceTransaction.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
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
		Transaccion other = (Transaccion) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (id != other.id)
			return false;
		if (savingAccountNumber == null) {
			if (other.savingAccountNumber != null)
				return false;
		} else if (!savingAccountNumber.equals(other.savingAccountNumber))
			return false;
		if (sourceTransaction == null) {
			if (other.sourceTransaction != null)
				return false;
		} else if (!sourceTransaction.equals(other.sourceTransaction))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}
	
	@Column(name="type", nullable= false)
	private String type;

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
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the sourceTransaction
	 */
	public String getSourceTransaction() {
		return sourceTransaction;
	}

	/**
	 * @param sourceTransaction the sourceTransaction to set
	 */
	public void setSourceTransaction(String sourceTransaction) {
		this.sourceTransaction = sourceTransaction;
	}

	/**
	 * @return the savingAccountNumber
	 */
	public CuentaAhorros getSavingAccountNumber() {
		return savingAccountNumber;
	}

	/**
	 * @param savingAccountNumber the savingAccountNumber to set
	 */
	public void setSavingAccountNumber(CuentaAhorros savingAccountNumber) {
		this.savingAccountNumber = savingAccountNumber;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	
	
	
	
}
