package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Funcionarios;
import br.com.fiap.domain.service.FuncionariosService;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/funcionario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource implements Resource<Funcionarios, Long>{


    @Context
    UriInfo uriInfo;

    FuncionariosService service = new FuncionariosService();

    @POST
    @Override
    public Response persist(Funcionarios funcionarios) {
        funcionarios.setId(null);
        Funcionarios persit = service.persist(funcionarios);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();

    }

    @GET
    @Override
    public Response findAll() {
        List<Funcionarios> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Funcionarios funcionarios = service.findById(id);
        if (Objects.isNull(funcionarios)) return Response.status(404).build();
        return Response.ok(funcionarios).build();
    }
}
