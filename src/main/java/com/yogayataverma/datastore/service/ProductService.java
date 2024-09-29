package com.yogayataverma.datastore.service;

import com.yogayataverma.datastore.model.Model;
import com.yogayataverma.datastore.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private Repo repository;

    public List<Model> findAll() {
        return repository.findAll();
    }

    public Model findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Model save(Model product) {
        return repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
