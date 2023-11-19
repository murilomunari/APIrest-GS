package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Paciente;

import java.util.List;

public class PacienteRepository implements Repository<Paciente, Long>{
    @Override
    public Paciente persist(Paciente paciente) {
        return null;
    }

    @Override
    public List<Paciente> findAll() {
        return null;
    }

    @Override
    public Paciente findById(Long aLong) {
        return null;
    }
}
