package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Funcionarios;
import br.com.fiap.domain.repository.FuncionarioRepository;

import java.util.List;

public class FuncionariosService implements Service<Funcionarios, Long>{

    private FuncionarioRepository repo = FuncionarioRepository.build();
    @Override
    public List<Funcionarios> findAll() {
        return repo.findAll();
    }

    @Override
    public Funcionarios findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Funcionarios persist(Funcionarios funcionarios) {
        return repo.persist(funcionarios);
    }
}
