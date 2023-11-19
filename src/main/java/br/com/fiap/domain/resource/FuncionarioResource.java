package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Funcionarios;
import com.sun.jna.platform.win32.WinDef;
import jakarta.ws.rs.core.Response;

public class FuncionarioResource implements Resource<Funcionarios, Long>{
    @Override
    public Response persist(Funcionarios funcionarios) {
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
