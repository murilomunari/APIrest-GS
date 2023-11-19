package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Paciente;
import jakarta.ws.rs.core.Response;

public class PacienteResource implements Resource<Paciente,Long>{
    @Override
    public Response persist(Paciente paciente) {
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
