package restapi;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/logement")
public class LogementRestApi {
    LogementBusiness LogementBusiness = new LogementBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAll(){
        return Response
                .ok()
                .entity(LogementBusiness.getLogements())
                .build();
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addLogement (Logement logement){
        LogementBusiness.addLogement(logement);
        return Response
                .status(201)
                .entity("logement a été ajouté avec succès")
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference){
        boolean isDeleted = LogementBusiness.deleteLogement(reference);
        if (isDeleted) {
            return Response
                    .ok("Logement supprimé avec succès").
                    build();
        }
        return Response
                .status(404)
                .entity("Logement non trouvé")
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{reference}")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement){
        boolean isUpdated = LogementBusiness.updateLogement(reference, logement);
        if (isUpdated) {
            return Response
                    .ok("Logement mis à jour avec succès")
                    .build();
        }
        return Response
                .status(404)
                .entity("Logement non trouvé")
                .build();
    }
}
