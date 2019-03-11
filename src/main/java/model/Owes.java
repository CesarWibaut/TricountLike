package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the owes database table.
 * 
 */
@Entity
@NamedQuery(name="Owes.findIfExist", query="Select o from Owes o where o.eno = :eno and o.uno=:uno and o.unoFor = :unoFor")
@NamedQuery(name="Owes.findByEno", query="Select o from Owes o where o.eno= :eno")
public class Owes implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ono;
    private Integer uno;
    private Integer unoFor;
    private Integer eno;
    private Float ammount;

    public Owes() {}

    /**
     * @param ono the ono to set
     */
    public void setOno(Integer ono) {
        this.ono = ono;
    }

    /**
     * @return the ono
     */
    public Integer getOno() {
        return ono;
    }

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

    public String toString() {
        return this.uno + " " + this.unoFor + " " + this.ammount;
    }

}