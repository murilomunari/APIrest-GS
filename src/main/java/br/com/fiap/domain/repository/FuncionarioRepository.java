package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Funcionarios;

import java.util.List;

public class FuncionarioRepository implements Repository<Funcionarios, Long>{
    @Override
    public Funcionarios persist(Funcionarios funcionarios) {
        return null;
    }

    @Override
    public List<Funcionarios> findAll() {
        return null;
    }

    @Override
    public Funcionarios findById(Long aLong) {
        return null;
    }
}
