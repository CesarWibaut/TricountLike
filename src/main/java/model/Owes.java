package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the owes database table.
 * 
 */
@Entity
@NamedQuery(name="Owes.findIfExist", query="Select o from Owes o where o.eno = :eno and o.uno=:uno and o.unoFor = :unoFor")
public class Owes implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private Integer uno;
    private Integer unoFor;
    private Integer eno;
    private Float ammount;

    public Owes() {}

    /**
     * @return the ammount
     */
    public Float getAmmount() {
        return ammount;
    }

    /**
     * @return the eno
     */
    public Integer getEno() {
        return eno;
    }

    /**
     * @return the enoFor
     */
    public Integer getUnoFor() {
        return unoFor;
    }

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(Float ammount) {
        this.ammount = ammount;
    }

    /**
     * @param eno the eno to set
     */
    public void setEno(Integer eno) {
        this.eno = eno;
    }

    /**
     * @param enoFor the enoFor to set
     */
    public void setUnoFor(Integer unoFor) {
        this.unoFor = unoFor;
    }

    /**
     * @param uno the uno to set
     */
    public void setUno(Integer uno) {
        this.uno = uno;
    }

    /**
     * @return the uno
     */
    public Integer getUno() {
        return uno;
    }

}