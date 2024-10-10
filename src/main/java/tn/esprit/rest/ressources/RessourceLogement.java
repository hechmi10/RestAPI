package tn.esprit.rest.ressources;

import tn.esprit.rest.entities.Logement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.esprit.rest.metiers.LogementBusiness;

@Path("logements")
public class RessourceLogement {
    private static List<Logement> logements = new ArrayList();
    public static LogementBusiness logB = new LogementBusiness();

    public RessourceLogement() {
    }

    @POST
    @Consumes({"application/xml"})
    public Response createLogement(Logement logement) {
        return logB.addLogement(logement) ? Response.status(Status.CREATED).build() : Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces({"application/json"})
    public Response getAllLogements() {
        return logB.getLogements().size() != 0 ? Response.status(Status.OK).entity(logB.getLogements()).build() : Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response getLogementsByDelegation(@QueryParam("delegation") String delegation) {
         List<Logement> l=logB.getLogementsByDeleguation(delegation);
         return !l.isEmpty() ? Response.status(Status.OK).build() :Response.status(Status.NOT_FOUND).build();
    }

    @GET
    public Response getLogementsListByRef(@QueryParam("reference") int reference){
        List<Logement> l=logB.getLogementsListeByref(reference);
        return !l.isEmpty()? Response.status(Status.OK).build():Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("{application/xml}")
    public Response updateLogement(@PathParam("id") int reference, Logement logement) {
        return logB.updateLogement(reference,logement) ? Response.status(Status.OK).build() : Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteLogement(@PathParam("id") int reception) {
        return logB.deleteLogement(reception) ? Response.status(Status.OK).build() : Response.status(Status.NOT_FOUND).build();
    }
}

