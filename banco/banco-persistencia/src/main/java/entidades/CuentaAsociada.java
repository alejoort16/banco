/**
 * 
 */
package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import enumeraciones.TipoDocumento;

/**
 * @author Alejandro Ortega
 *
 */
@NamedQueries({
		@NamedQuery(name = CuentaAsociada.findbyCliente, query = "select c from CuentaAsociada c where c.clientePrincipal = ?1 AND c.estado= 'Asociada'"),
		@NamedQuery(name = CuentaAsociada.CuentasAsociadas, query = "select c from CuentaAsociada c where c.clientePrincipal = ?1") })
@Entity
@Table(name = "T_CUENTA_ASOCIADA")
public class CuentaAsociada implements Serializable {

	public static final String findbyCliente = "listarCuentasAsociadasVerificadas";
	public static final String CuentasAsociadas = "listarCuentasAsociadas";

	@Id
	@Column(name = "numero_cuenta", nullable = false, length = 20)
	private String numeroCuenta;

	@Column(name = "nombre_asociacion", nullable = false, length = 50)
	private String nombreAsociacion;

	@Column(name = "nombre_titular", nullable = false, length = 50)
	private String nombreTitular;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_documento", nullable = false, length = 10)
	private TipoDocumento tipoDocumento;

	@Column(name = "numero_documento", nullable = false, length = 15)
	private String numeroDocumento;

	@Column(name = "estado", nullable = false, length = 20)
	private String estado;

	@JoinColumn(name = "banco")
	@ManyToOne
	private Banco banco;

	@JoinColumns({

			@JoinColumn(name = "identificationType", referencedColumnName = "identification_type"),
			@JoinColumn(name = "identificationNumber", referencedColumnName = "identification_number") })
	@ManyToOne
	private Cliente clientePrincipal;

	public CuentaAsociada() {

	}

	public CuentaAsociada(String numeroCuenta, String nombreAsociacion, String nombreTitular,
			TipoDocumento tipoDocumento, String numeroDocumento, String estado, Banco banco, Cliente clientePrincipal) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.nombreAsociacion = nombreAsociacion;
		this.nombreTitular = nombreTitular;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.estado = estado;
		this.banco = banco;
		this.clientePrincipal = clientePrincipal;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombreAsociacion
	 */
	public String getNombreAsociacion() {
		return nombreAsociacion;
	}

	/**
	 * @param nombreAsociacion
	 *            the nombreAsociacion to set
	 */
	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
	}

	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular
	 *            the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento
	 *            the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * @return the clientePrincipal
	 */
	public Cliente getClientePrincipal() {
		return clientePrincipal;
	}

	/**
	 * @param clientePrincipal
	 *            the clientePrincipal to set
	 */
	public void setClientePrincipal(Cliente clientePrincipal) {
		this.clientePrincipal = clientePrincipal;
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
		result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
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
		CuentaAsociada other = (CuentaAsociada) obj;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
