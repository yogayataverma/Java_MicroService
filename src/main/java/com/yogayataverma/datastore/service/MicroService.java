package com.yogayataverma.datastore.service;

import com.yogayataverma.datastore.model.Micro;
import com.yogayataverma.datastore.repository.Repo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MicroService {
    private final Repo repository;

    public MicroService(Repo repository) {
        this.repository = repository;
    }

    public List<Micro> findAll() {
        return repository.findAll();
    }

    public Micro findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Micro save(Micro person) {
        return repository.save(person);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
