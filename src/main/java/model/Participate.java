package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the participate database table.
 * 
 */
@Entity
@NamedQuery(name="Participate.findAll", query="SELECT p FROM Participate p")
public class Participate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer eno;

	private Integer uno;

	public Participate() {
	}

	public Integer getEno() {
		return this.eno;
	}

	public void setEno(Integer eno) {
		this.eno = eno;
	}

	public Integer getUno() {
		return this.uno;
	}

	public void setUno(Integer uno) {
		this.uno = uno;
	}

}