package org.springframework.samples.petclinic.customers.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.samples.petclinic.api.core.dynamodb.AbstractDynamoDBRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.samples.petclinic.customers.model.Owner.BY_LAST_NAME;

@Component
public class OwnerRepositoryImpl extends AbstractDynamoDBRepository<Owner, String> implements OwnerRepository  {

    @Inject
    public OwnerRepositoryImpl(DynamoDBMapper mapper) {
        super(Owner.class, mapper);
    }

    @Override
    protected Owner toId(String id) {
        return Owner.builder().id(id).build();
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return findByIndex(BY_LAST_NAME, Owner.builder().lastName(lastName).build());
    }
}
