package restapi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/hello")
public class HelloRestApi {
 @GET
 @Produces(MediaType.TEXT_PLAIN)
 @Path("/hi")
    public Response sayHi() {
        return Response.
                status(200).
                entity("Hi 2cinfo").
                build();
    }
    //api/hello/ahmed/
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public Response sayHiTo (@PathParam(value = "name") String name
                             ,@QueryParam(value = "lName" ) String lName){
        return Response.status(200)
                .entity("hello "+name+" "+lName)
                .build();
    }



}
