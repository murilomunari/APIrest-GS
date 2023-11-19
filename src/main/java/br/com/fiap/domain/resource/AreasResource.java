package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Areas;
import jakarta.ws.rs.core.Response;

public class AreasResource implements Resource<Areas, Long>{
    @Override
    public Response persist(Areas areas) {
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
