/**
 * 
 */
package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Alejandro Ortega
 *
 */
@NamedQueries({
@NamedQuery(name=Banco.findAll, query="select b from Banco b"),
@NamedQuery(name=Banco.findByName, query="select b from Banco b where b.name = ?1")
})
@Entity
@Table(name="T_BANK")
public class Banco implements Serializable{

	public static final String findAll = "listarBancos";
	public static final String findByName = "buscarPorNombreBanco";


	@Id
	@Column(name="id", nullable=false, length=4)
	private String id;
	
	@Column(name="name", nullable=false, length=50)
	private String name;

	public Banco(){
		
	}
	
	
	
	public Banco(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Banco other = (Banco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
