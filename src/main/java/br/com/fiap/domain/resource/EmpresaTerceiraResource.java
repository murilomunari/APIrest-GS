package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.EmpresaTerciera;
import br.com.fiap.domain.service.EmpresaTerceiraService;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/empresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaTerceiraResource implements Resource<EmpresaTerciera, Long>{

    @Context
    UriInfo uriInfo;

    EmpresaTerceiraService service = new EmpresaTerceiraService();

    @POST
    @Override
    public Response persist(EmpresaTerciera empresaTerciera) {
        empresaTerciera.setId(null);
        EmpresaTerciera persit = service.persist(empresaTerciera);

        if (Objects.isNull(persit)) return Response.status(404).build();

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();

        return Response.created(uri).entity(persit).build();
    }

    @GET
    @Override
    public Response findAll() {
        List<EmpresaTerciera> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        EmpresaTerciera empresa = service.findById(id);
        if (Objects.isNull(empresa)) return Response.status(404).build();
        return Response.ok(empresa).build();
    }
}
