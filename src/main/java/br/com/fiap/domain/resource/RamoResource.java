package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Ramo;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.core.Response;

public class RamoResource implements Resource<Ramo, Long>{
    @Override
    public Response persist(Ramo ramo) {
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
