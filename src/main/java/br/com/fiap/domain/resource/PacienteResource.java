package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Paciente;
import br.com.fiap.domain.service.PacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/paciente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource implements Resource<Paciente,Long>{


    @Context
    UriInfo uriInfo;

    PacienteService service = new PacienteService();

    @POST
    @Override
    public Response persist(Paciente paciente) {
        paciente.setId(null);
        Paciente persit = service.persist(paciente);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();

    }

    @GET
    @Override
    public Response findAll() {
        List<Paciente> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Paciente paciente = service.findById(id);
        if (Objects.isNull(paciente)) return Response.status(404).build();
        return Response.ok(paciente).build();
    }
}
