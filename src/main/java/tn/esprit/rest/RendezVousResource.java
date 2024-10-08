package tn.esprit.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/rendezvous")
@Produces("application/json")
@Consumes("application/json")
public class RendezVousResource {

    private static List<RendezVous> rendezVousList = new ArrayList<>();

    // Création d'un nouveau rendez-vous
    @POST
    public Response createRendezVous(RendezVous rendezVous) {
        rendezVousList.add(rendezVous);
        return Response.status(Response.Status.CREATED).build();
    }

    // Récupération de la liste de tous les rendez-vous
    @GET
    public List<RendezVous> getAllRendezVous() {
        return rendezVousList;
    }

    // Récupération des rendez-vous pour un logement spécifique
    @GET
    @Path("/search")
    public List<RendezVous> getRendezVousByLogement(@QueryParam("refLogement") int refLogement) {
        List<RendezVous> result = new ArrayList<>();
        for (RendezVous r : rendezVousList) {
            if (r.getLogement().getReference() == refLogement) {
                result.add(r);
            }
        }
        return result;
    }

    // Suppression d'un rendez-vous
    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        RendezVous rendezVousToRemove = null;
        for (RendezVous r : rendezVousList) {
            if (r.getId() == id) {
                rendezVousToRemove = r;
                break;
            }
        }
        if (rendezVousToRemove != null) {
            rendezVousList.remove(rendezVousToRemove);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Mise à jour d'un rendez-vous
    @PUT
    @Path("/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        for (RendezVous r : rendezVousList) {
            if (r.getId() == id) {
                r.setDate(rendezVous.getDate());
                r.setHeure(rendezVous.getHeure());
                r.setLogement(rendezVous.getLogement());
                r.setNumTel(rendezVous.getNumTel());
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

