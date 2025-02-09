package restapi;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousRestApi {
    RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAll() {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVous();
        return Response.ok().entity(rendezVousList).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addRendezVous(RendezVous rendezVous) {
        rendezVousBusiness.addRendezVous(rendezVous);
        return Response
                .status(201)
                .entity("Le rendez-vous a été ajouté avec succès")
                .build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean updated = rendezVousBusiness.updateRendezVous(id, rendezVous);
        if (updated) {
            return Response
                    .ok("Le rendez-vous a été mis à jour avec succès")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("Rendez-vous non trouvé")
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response
                    .ok("Le rendez-vous a été supprimé avec succès")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("Rendez-vous non trouvé")
                    .build();
        }
    }
}

