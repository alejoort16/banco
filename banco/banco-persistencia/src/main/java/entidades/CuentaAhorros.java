package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "T_SAVING_ACCOUNT")
@NamedQueries({
	@NamedQuery( name = CuentaAhorros.listarCuentaAhorro, query="SELECT c FROM CuentaAhorros c WHERE c.cliente = ?1")
})

public class CuentaAhorros extends Producto implements Serializable {
	
	public static final String listarCuentaAhorro = "listarCuentasAhorro";

	@Column(name = "saving_interest", nullable = false)
	private double savingInterest;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(savingInterest);
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaAhorros other = (CuentaAhorros) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (Double.doubleToLongBits(savingInterest) != Double.doubleToLongBits(other.savingInterest))
			return false;
		return true;
	}

	/**
	 * @return the savingInterest
	 */
	public double getSavingInterest() {
		return savingInterest;
	}

	/**
	 * @param savingInterest
	 *            the savingInterest to set
	 */
	public void setSavingInterest(double savingInterest) {
		this.savingInterest = savingInterest;
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

	public CuentaAhorros(String number, Date expedition_date, Cliente cliente, double savingInterest, double ammount) {
		super(number, expedition_date, cliente);
		this.savingInterest = savingInterest;
		this.ammount = ammount;
	}

	public CuentaAhorros() {

	}

}
