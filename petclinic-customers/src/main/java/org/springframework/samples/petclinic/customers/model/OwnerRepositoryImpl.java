package org.springframework.samples.petclinic.customers.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class OwnerRepositoryImpl implements OwnerRepository {

    @Override
    public Owner findOne(int ownerId) {
        return new Owner();
    }

    @Override
    public Owner save(Owner owner) {
        return owner;
    }

    @Override
    public List<Owner> findAll() {
        return asList(findOne(0));
    }
}
