package com.unifei.ProfScore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBase<T, ID, JTA extends JpaRepository<T, ID>> {
    @Autowired
    private JTA repository;

    public Optional<T> getId(ID id) {
        return repository.findById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T create(T obj) {
        return repository.save(obj);
    }

    public T update(T obj) {
        return repository.save(obj);
    }

    public void delete(T obj) {
        repository.delete(obj);
    }

    public void deleteId(ID id) {
        repository.deleteById(id);
    }
}