package com.yogayataverma.datastore.controller;

import com.yogayataverma.datastore.model.Micro;
import com.yogayataverma.datastore.service.MicroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class Controller {
    private final MicroService service;

    public Controller(MicroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Micro>> getAllPersons() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Micro> getPersonById(@PathVariable Long id) {
        Micro person = service.findById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Micro> createPerson(@RequestBody Micro person) {
        return ResponseEntity.ok(service.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Micro> updatePerson(@PathVariable Long id, @RequestBody Micro personDetails) {
        Micro person = service.findById(id);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setEmail(personDetails.getEmail());
            person.setAge(personDetails.getAge());
            return ResponseEntity.ok(service.save(person));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (service.findById(id) != null) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
