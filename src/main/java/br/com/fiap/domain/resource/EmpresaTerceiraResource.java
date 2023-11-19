package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.EmpresaTerciera;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.core.Response;

public class EmpresaTerceiraResource implements Resource<EmpresaTerciera, Long>{
    @Override
    public Response persist(EmpresaTerciera empresaTerciera) {
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
