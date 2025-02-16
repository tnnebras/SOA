package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousResource {

    static private RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousBusiness.addRendezVous(rendezVous);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(rendezVous).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Unable to create rendezvous").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVous();
        return Response.status(Response.Status.OK).entity(rendezVousList).build();
    }

    @GET
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogement(@PathParam("reference") int reference) {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        return Response.status(Response.Status.OK).entity(rendezVousList).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous not found").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
        if (updated) {
            return Response.status(Response.Status.OK).entity(updatedRendezVous).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous not found").build();
    }
}
