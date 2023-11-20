package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Areas;
import br.com.fiap.domain.service.AreasService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/area")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AreasResource implements Resource<Areas, Long>{

    @Context
    UriInfo uriInfo;
    AreasService service = new AreasService();

    @POST
    @Override
    public Response persist(Areas areas) {
        areas.setId(null);
        Areas persit = service.persist(areas);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();
    }


    @GET
    @Override
    public Response findAll() {
        List<Areas> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Areas areas = service.findById(id);
        if (Objects.isNull(areas)) return Response.status(404).build();
        return Response.ok(areas).build();
    }
}
