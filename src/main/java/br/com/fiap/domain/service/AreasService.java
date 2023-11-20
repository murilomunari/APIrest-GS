package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Areas;
import br.com.fiap.domain.repository.AreasRepository;

import java.util.List;

public class AreasService implements Service<Areas, Long>{

    private AreasRepository repo = AreasRepository.build();
    @Override
    public List<Areas> findAll() {
        return repo.findAll();
    }

    @Override
    public Areas findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Areas persist(Areas areas) {
        return repo.persist(areas);
    }
}
