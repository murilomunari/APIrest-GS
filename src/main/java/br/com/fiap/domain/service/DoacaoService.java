package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Doacao;
import br.com.fiap.domain.repository.DoacaoRepository;

import java.util.List;

public class DoacaoService implements Service<Doacao, Long>{

    private DoacaoRepository repo = DoacaoRepository.build();
    @Override
    public List<Doacao> findAll() {
        return repo.findAll();
    }

    @Override
    public Doacao findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Doacao persist(Doacao doacao) {
        return repo.persist(doacao);
    }
}
