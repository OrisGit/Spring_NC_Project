package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DataControl<T,R> {
    Iterable<T> getAll();
    T get(R id);
    T saveOrUpdate(T entity) throws Exception;
    void delete(T entity) throws Exception;
    void deleteById(R id) throws Exception;
    boolean exists(R id);
    Page<T> findAll(Pageable pageable);
}
