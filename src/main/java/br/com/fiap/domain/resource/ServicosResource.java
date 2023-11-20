package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Servicos;
import br.com.fiap.domain.service.ServicosService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/servico")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServicosResource implements Resource<Servicos,Long>{

    @Context
    UriInfo uriInfo;

    ServicosService service = new ServicosService();

    @POST
    @Override
    public Response persist(Servicos servicos) {
        servicos.setId(null);
        Servicos persit = service.persist(servicos);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();
    }

    @GET
    @Override
    public Response findAll() {
        List<Servicos> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Servicos servicos = service.findById(id);
        if (Objects.isNull(servicos)) return Response.status(404).build();
        return Response.ok(servicos).build();
    }
}
