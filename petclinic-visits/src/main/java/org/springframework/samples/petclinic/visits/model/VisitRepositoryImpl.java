package org.springframework.samples.petclinic.visits.model;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class VisitRepositoryImpl implements VisitRepository {

    @Override
    public List<Visit> findByPetId(int petId) {
        return asList(new Visit());
    }

    @Override
    public void save(Visit visit) {

    }
}
