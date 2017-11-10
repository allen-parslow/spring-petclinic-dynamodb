package org.springframework.samples.petclinic.api.core.io;

public interface SimpleCrudRepository<T, ID> {

    void update(T entity);

    T read(ID id);
}
