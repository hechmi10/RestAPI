package tn.esprit.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.List;

@ApplicationPath("/tn/esprit/rest")
public class RestActivator extends Application {
    public static void main(String[] args) {
        LogementResource lr = new LogementResource();
        RendezVousResource rvr=new RendezVousResource();
        Logement l1=new Logement(1,"Rue des roses","El Ghazela","Ariana","Studio","cuisine",300);
        lr.createLogement(l1);
        RendezVous rv1=new RendezVous(1,"09/08/2025","15:30",l1,"29771536");
        rvr.createRendezVous(rv1);
        System.out.println(Arrays.toString(new List[]{lr.getAllLogements()}));
        System.out.println(Arrays.toString(new List[]{rvr.getAllRendezVous()}));
    }
}
