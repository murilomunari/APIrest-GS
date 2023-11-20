package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Ramo;
import br.com.fiap.domain.repository.RamoRepository;

import java.util.List;

public class RamoService implements Service<Ramo, Long> {

    private RamoRepository repo = RamoRepository.build();
    @Override
    public List<Ramo> findAll() {
        return repo.findAll();
    }

    @Override
    public Ramo findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Ramo persist(Ramo ramo) {
        return repo.persist(ramo);
    }
}
