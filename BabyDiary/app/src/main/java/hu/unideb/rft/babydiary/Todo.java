package hu.unideb.rft.babydiary;

/**
 * Created by Vozarpet on 2018. 11. 16..
 */

public class Todo {

    private int id;
    private String felhasznalonev;
    private String cim;
    private String leiras;
    private String dateum;
    private String ido;
    private String helyszin;
    private int prioritas;


    public Todo() {
    }

    public Todo(int id, String felhasznalonev, String cim, String leiras, String dateum, String ido, String helyszin, int prioritas) {
        this.id = id;
        this.felhasznalonev = felhasznalonev;
        this.cim = cim;
        this.leiras = leiras;
        this.dateum = dateum;
        this.ido = ido;
        this.helyszin = helyszin;
        this.prioritas = prioritas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getDateum() {
        return dateum;
    }

    public void setDateum(String dateum) {
        this.dateum = dateum;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        this.prioritas = prioritas;
    }
}
