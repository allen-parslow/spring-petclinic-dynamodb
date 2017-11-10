package org.springframework.samples.petclinic.vets.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.samples.petclinic.api.core.dynamodb.AbstractDynamoDBRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class VetRepositoryImpl extends AbstractDynamoDBRepository<Vet, String> implements VetRepository {

    @Inject
    public VetRepositoryImpl(DynamoDBMapper mapper) {
        super(Vet.class, mapper);
    }


    @Override
    protected Vet toId(String id) {
        return Vet.builder().id(id).build();
    }
}

