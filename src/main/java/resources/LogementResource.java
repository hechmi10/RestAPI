package resources;

import entity.Logement;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/logements")
@Produces("application/json")
@Consumes("application/xml")
public class LogementResource {

    private static List<Logement> logements = new ArrayList<>();

    // Création d'un nouveau logement
    @POST
    public Response createLogement(Logement logement) {
        logements.add(logement);
        return Response.status(Response.Status.CREATED).build();
    }

    // Récupération de la liste de tous les logements
    @GET
    public List<Logement> getAllLogements() {
        return logements;
    }

    // Récupération de logements par délégation
    @GET
    @Path("/search")
    public List<Logement> getLogementsByDelegation(@QueryParam("delegation") String delegation) {
        List<Logement> result = new ArrayList<>();
        for (Logement l : logements) {
            if (l.getDelegation().equalsIgnoreCase(delegation)) {
                result.add(l);
            }
        }
        return result;
    }

    // Suppression d'un logement
    @DELETE
    @Path("/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        Logement logementToRemove = null;
        for (Logement l : logements) {
            if (l.getReference() == reference) {
                logementToRemove = l;
                break;
            }
        }
        if (logementToRemove != null) {
            logements.remove(logementToRemove);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Mise à jour d'un logement
    @PUT
    @Path("/{reference}")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        for (Logement l : logements) {
            if (l.getReference() == reference) {
                l.setAdresse(logement.getAdresse());
                l.setDelegation(logement.getDelegation());
                l.setGouvernorat(logement.getGouvernorat());
                l.setType(logement.getType());
                l.setDescription(logement.getDescription());
                l.setPrix(logement.getPrix());
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

