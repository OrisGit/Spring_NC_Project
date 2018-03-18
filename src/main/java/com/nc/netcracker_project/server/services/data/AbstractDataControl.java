package com.nc.netcracker_project.server.services.data;

import com.nc.netcracker_project.server.model.entities.DrugEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractDataControl<T, R extends Serializable> implements DataControl<T,R> {

    protected JpaRepository<T,R> repository;

    protected AbstractDataControl(JpaRepository<T,R> repository) {
        this.repository = repository;
    }

    @Override
    public T saveOrUpdate(T entity) throws Exception {
        try{
            repository.save(entity);
        }catch (Exception e){
            throw new Exception();
        }
        return entity;
    }

    @Override
    public Iterable<T> getAll(){
        return repository.findAll();
    }

    @Override
    public T get(R id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(T entity) throws Exception {
        try{
            repository.delete(entity);
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public boolean exists(R id) {
        return repository.exists(id);
    }

    @Override
    public void deleteById(R id) throws Exception {
        try {
            repository.delete(id);
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public List<T> findAll(int pageNumber) {
        PageRequest pageable = new PageRequest(pageNumber,20, Sort.Direction.ASC, "name");
        return (List<T>) this.repository.findAll(pageable);
    }
}
