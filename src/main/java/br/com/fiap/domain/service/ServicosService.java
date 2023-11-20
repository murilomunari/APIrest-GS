package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Servicos;
import br.com.fiap.domain.repository.ServicosRepository;

import java.util.List;

public class ServicosService implements Service<Servicos, Long>{

    private ServicosRepository repo = ServicosRepository.build();
    @Override
    public List<Servicos> findAll() {
        return repo.findAll();
    }

    @Override
    public Servicos findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Servicos persist(Servicos servicos) {
        return repo.persist(servicos);
    }
}
