package com.yogayataverma.datastore.repository;

import com.yogayataverma.datastore.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Model, Long> {
}
