package tn.esprit.rest.metiers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import tn.esprit.rest.entities.Logement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogementBusiness {
    private List<Logement> logements = new ArrayList();

    public LogementBusiness() {
        this.logements.add(new Logement(1, "27, Rue des roses", "El ghazela", "Ariana", "Studio", "cuisine equipee", 300.0F));
        this.logements.add(new Logement(5, "58, Rue des roses", "El ghazela", "Ariana", "EtageVilla", "cuisine equipee", 450.0F));
        this.logements.add(new Logement(2, "201, R�sidence Omrane4", "Riadh El Andalous", "Ariana", "EtageVilla", "chauffage central, ascenseur, climatisation", 700.0F));
        this.logements.add(new Logement(3, "540, R�sidence Les Tulipes", "El Aouina", "Ariana", "Appartement", "S+2, chauffage central, ascenseur, climatisation", 500.0F));
        this.logements.add(new Logement(4, "78, Rue des Oranges", "Bardo", "Tunis", "EtageVilla", "chauffage central, ascenseur, climatisation", 400.0F));
    }

    public Logement getLogementsByReference(int reference) {
        Iterator var2 = this.logements.iterator();

        Logement l;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            l = (Logement)var2.next();
        } while(l.getReference() != reference);

        return l;
    }

    public boolean addLogement(Logement logement) {
        return this.logements.add(logement);
    }

    public List<Logement> getLogementsByDeleguation(String deleguation) {
        List<Logement> liste = new ArrayList();
        Iterator var3 = this.logements.iterator();

        while(var3.hasNext()) {
            Logement l = (Logement)var3.next();
            if (l.getDelegation().equals(deleguation)) {
                liste.add(l);
            }
        }

        return liste;
    }

    public List<Logement> getLogementsListeByref(int reference) {
        List<Logement> liste = new ArrayList();
        Iterator var3 = this.logements.iterator();

        while(var3.hasNext()) {
            Logement l = (Logement)var3.next();
            if (l.getReference() == reference) {
                liste.add(l);
            }
        }

        return liste;
    }

    public boolean deleteLogement(int reference) {
        Iterator<Logement> iterator = this.logements.iterator();

        Logement l;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            l = (Logement)iterator.next();
        } while(l.getReference() != reference);

        iterator.remove();
        return true;
    }

    public boolean updateLogement(int reference, Logement logement) {
        int index = -1;

        for(int i = 0; i < this.logements.size(); ++i) {
            if (((Logement)this.logements.get(i)).getReference() == reference) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            this.logements.set(index, logement);
            return true;
        } else {
            return false;
        }
    }

    public List<Logement> getLogements() {
        return this.logements;
    }

    public void setLogements(List<Logement> logements) {
        this.logements = logements;
    }
}

