package tn.esprit.rest.ressources;

import tn.esprit.rest.entities.RendezVous;
import tn.esprit.rest.filtres.Secured;
import tn.esprit.rest.metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("rendezvous")
@Produces("application/json")
@Consumes("application/json")
public class RendezVousResource {

    private static List<RendezVous> rendezVousList = new ArrayList<>();
    private RendezVousBusiness rvB=new RendezVousBusiness();

    @POST
    public Response createRendezVous(RendezVous rendezVous) {
        return rvB.addRendezVous(rendezVous) ? Response.ok().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    public Response getAllRendezVous() {

        return rvB.getListeRendezVous() ? Response.status(Response.Status.OK).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    /*@GET
    public Response getRendezVousByLogement(@QueryParam("refLogement") int refLogement) {
        rvB.getListeRendezVousByLogementReference(refLogement);
        return Response.ok().build();
    }*/

    // Suppression d'un rendez-vous
    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        return rvB.deleteRendezVous(id) ? Response.ok().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    // Mise à jour d'un rendez-vous
    @PUT
    @Path("/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        return rvB.updateRendezVous(id,rendezVous) ? Response.ok().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }
}

