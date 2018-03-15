package com.nc.netcracker_project.server.services.data;

public interface DataControl<T,R> {
    Iterable<T> getAll();
    T get(R id);
    T saveOrUpdate(T entity) throws Exception;
    void delete(T entity) throws Exception;
    void deleteById(R id) throws Exception;
    boolean exists(R id);
}
