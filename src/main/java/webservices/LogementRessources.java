package webservices;

import metiers.LogementBusiness;
import entities.Logement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
public class LogementRessources {

    static LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    @GET
    @Path("/get/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = help.getLogementsByReference(reference);
        if (logement != null) {
            return Response
                    .status(200)
                    .entity(logement)
                    .build();
        }
        return Response
                .status(404)
                .entity("Logement not found")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response
                    .status(201)
                    .entity(logement)
                    .build();
        }
        return Response
                .status(400)
                .entity("Failed to add logement")
                .build();
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response
                    .status(200)
                    .entity(logement)
                    .build();
        }
        return Response
                .status(400)
                .entity("Failed to update logement")
                .build();
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response
                    .status(200)
                    .entity("Logement deleted")
                    .build();
        }
        return Response
                .status(404)
                .entity("Logement not found")
                .build();
    }
}
