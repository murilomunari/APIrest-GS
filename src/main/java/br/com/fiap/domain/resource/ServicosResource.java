package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Servicos;
import jakarta.ws.rs.core.Response;

public class ServicosResource implements Resource<Servicos,Long>{
    @Override
    public Response persist(Servicos servicos) {
        return null;
    }

    @Override
    public Response findAll() {
        return null;
    }

    @Override
    public Response findById(Long aLong) {
        return null;
    }
}
