package model;

/**
 * FormattedOwe
 */
public class FormattedOwe {

    private String uno;
    private String unoFor;
    private float ammount;

    /**
     * @param ammount the ammount to set
     */
    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }
    /**
     * @param uno the uno to set
     */
    public void setUno(String uno) {
        this.uno = uno;
    }
    /**
     * @param unoFor the unoFor to set
     */
    public void setUnoFor(String unoFor) {
        this.unoFor = unoFor;
    }
    /**
     * @return the ammount
     */
    public float getAmmount() {
        return ammount;
    }
    /**
     * @return the uno
     */
    public String getUno() {
        return uno;
    }
    /**
     * @return the unoFor
     */
    public String getUnoFor() {
        return unoFor;
    }

    public String toString() {
        return uno + " owes " + ammount + " euros to " + unoFor;
    }
}