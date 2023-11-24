package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Areas;
import br.com.fiap.domain.entity.Doacao;
import br.com.fiap.domain.service.DoacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/doacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoacaoResource implements Resource<Doacao, Long>{

    @Context
    UriInfo uriInfo;

    DoacaoService service = new DoacaoService();

    @POST
    @Override
    public Response persist(Doacao doacao) {
        doacao.setId(null);
        Doacao persist = service.persist(doacao);

        if (Objects.isNull(persist)) return  Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persist.getId())).build();

        return Response.created(uri).entity(persist).build();
    }

    @GET
    @Override
    public Response findAll() {
        List<Doacao> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Doacao doacao = service.findById(id);
        if (Objects.isNull(doacao)) return Response.status(404).build();
        return Response.ok(doacao).build();
    }
}
