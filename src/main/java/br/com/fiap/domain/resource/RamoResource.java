package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Ramo;
import br.com.fiap.domain.service.RamoService;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/ramo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RamoResource implements Resource<Ramo, Long>{

    @Context
    UriInfo uriInfo;

    RamoService service = new RamoService();

    @POST
    @Override
    public Response persist(Ramo ramo) {
        ramo.setId(null);
        Ramo persit = service.persist(ramo);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();
    }

    @GET
    @Override
    public Response findAll() {
        List<Ramo> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Ramo ramo = service.findById(id);
        if (Objects.isNull(ramo)) return Response.status(404).build();
        return Response.ok(ramo).build();
    }
}
