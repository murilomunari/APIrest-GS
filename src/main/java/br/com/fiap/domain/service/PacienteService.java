package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Paciente;
import br.com.fiap.domain.repository.PacienteRepository;

import java.util.List;

public class PacienteService implements Service<Paciente, Long>{

    private PacienteRepository repo = PacienteRepository.build();
    @Override
    public List<Paciente> findAll() {
        return repo.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Paciente persist(Paciente paciente) {
        return repo.persist(paciente);
    }
}
