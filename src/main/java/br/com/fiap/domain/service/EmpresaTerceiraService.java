package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.EmpresaTerciera;
import br.com.fiap.domain.repository.EmpresaTerceiraRepository;

import java.util.List;

public class EmpresaTerceiraService implements Service<EmpresaTerciera, Long>{

    private EmpresaTerceiraRepository repo = EmpresaTerceiraRepository.build();
    @Override
    public List<EmpresaTerciera> findAll() {
        return repo.findAll();
    }

    @Override
    public EmpresaTerciera findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public EmpresaTerciera persist(EmpresaTerciera empresaTerciera) {
        return repo.persist(empresaTerciera);
    }
}
