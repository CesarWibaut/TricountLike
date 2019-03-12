package model;

import java.util.*;
import java.util.stream.Collectors;

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

    public static List<FormattedOwe> balance(List<FormattedOwe> list) {
        for(int i = list.size()-1; i >= 0 && i < list.size(); i--) {
            for(int j = list.size()-1; j >= 0 && j < list.size(); j--){
                if(list.get(i).getUno().equals(list.get(j).getUnoFor()) && list.get(j).getUno().equals(list.get(i).getUnoFor())) {
                    if(list.get(i).getAmmount() - list.get(j).getAmmount() > 0) {
                        list.get(i).setAmmount(list.get(i).getAmmount() - list.get(j).getAmmount());
                        list.remove(j);
                    } else if (list.get(i).getAmmount() - list.get(j).getAmmount() < 0){
                        list.get(j).setAmmount((list.get(i).getAmmount() - list.get(j).getAmmount()) * -1 );
                        list.remove(i);
                    } else {
                        list.get(i).setAmmount(0);
                        list.remove(j);
                    }
                }
            }
        }
        list = list.parallelStream().filter(o -> o.getAmmount() !=0).collect(Collectors.toList());
        return list;
    }
}