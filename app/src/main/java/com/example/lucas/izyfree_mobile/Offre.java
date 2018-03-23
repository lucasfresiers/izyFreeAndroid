package com.example.lucas.izyfree_mobile;

/**
 * Created by bertins on 23/03/18.
 */

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

/*curl -XPOST -H "Content-type: application/json" -d '{"intitule":"dev python", "dateDeb":"01/04/2018", "dateFin":"28/04/2018", "listeMots":"dev?java?projet"}' http://localhost:8080/v1/offre
*/

public class Offre {
    private int id = 0;
    private String intitule;
    private String dateDeb;
    private String dateFin;
    private String listeMots;
    private int idEntreprise;

    public Offre(int id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public Offre(int id, String intitule, String dateDeb, String dateFin, String listeMots, int idEntreprise) {
        this.id = id;
        this.intitule = intitule;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.listeMots = listeMots;
        this.idEntreprise = idEntreprise;
    }

    public Offre() {
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntrepise) {
        this.idEntreprise = idEntrepise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }


    public void setListeMots(String listeMots) {
        this.listeMots = listeMots;
    }

    public String getListeMots() {
        return listeMots;
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Offre offre = (Offre) arg;
        return intitule.equals(offre.intitule) && dateDeb.equals(offre.dateDeb) && dateFin.equals(offre.dateFin)
                && listeMots.equals(offre.listeMots) && idEntreprise == offre.idEntreprise;
    }
}
