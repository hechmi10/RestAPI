package tn.esprit.rest.metiers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import tn.esprit.rest.entities.Logement;
import tn.esprit.rest.entities.RendezVous;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RendezVousBusiness {
    public static List<RendezVous> listeRendezVous;
    LogementBusiness logementMetier = new LogementBusiness();

    public RendezVousBusiness() {
        listeRendezVous = new ArrayList();
    }

    public boolean addRendezVous(RendezVous rendezVous) {
        int refLogement = rendezVous.getLogement().getReference();
        Logement logement = this.logementMetier.getLogementsByReference(refLogement);
        if (logement != null) {
            rendezVous.setLogement(logement);
            return listeRendezVous.add(rendezVous);
        } else {
            return false;
        }
    }

    public boolean getListeRendezVous() {
        return !listeRendezVous.isEmpty();
    }

    public void setListeRendezVous(List<RendezVous> listeRendezVous) {
        RendezVousBusiness.listeRendezVous = listeRendezVous;
    }

    public List<RendezVous> getListeRendezVousByLogementReference(int reference) {
        List<RendezVous> liste = new ArrayList();
        Iterator var3 = listeRendezVous.iterator();

        while(var3.hasNext()) {
            RendezVous r = (RendezVous)var3.next();
            if (r.getLogement().getReference() == reference) {
                liste.add(r);
            }
        }

        return liste;
    }

    public RendezVous getRendezVousById(int id) {
        RendezVous rendezVous = null;
        Iterator var3 = listeRendezVous.iterator();

        while(var3.hasNext()) {
            RendezVous r = (RendezVous)var3.next();
            if (r.getId() == id) {
                rendezVous = r;
            }
        }

        return rendezVous;
    }

    public boolean deleteRendezVous(int id) {
        Iterator<RendezVous> iterator = listeRendezVous.iterator();
        RendezVous r;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            r = (RendezVous)iterator.next();
        } while(r.getId() != id);

        iterator.remove();
        return true;
    }

    public boolean updateRendezVous(int idRendezVous, RendezVous updatedRendezVous) {
        for(int i = 0; i < listeRendezVous.size(); ++i) {
            RendezVous r = (RendezVous)listeRendezVous.get(i);
            if (r.getId() == idRendezVous) {
                Logement logement = this.logementMetier.getLogementsByReference(updatedRendezVous.getLogement().getReference());
                if (logement != null) {
                    listeRendezVous.set(i, updatedRendezVous);
                    return true;
                }
            }
        }

        return false;
    }
}

