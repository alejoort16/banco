package entidades;

import java.io.Serializable;

import enumeraciones.TipoDocumento;

public class ClientePK implements Serializable {

	private TipoDocumento identificationType;
	private String identificationNumber;

	public ClientePK() {

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
	
	

	public ClientePK(TipoDocumento identificationType, String identificationNumber) {
		super();
		this.identificationType = identificationType;
		this.identificationNumber = identificationNumber;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificationNumber == null) ? 0 : identificationNumber.hashCode());
		result = prime * result + ((identificationType == null) ? 0 : identificationType.hashCode());
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
		ClientePK other = (ClientePK) obj;
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
		return true;
	}
	
	
	
	
}
