package org.springframework.samples.petclinic.vets.model;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VetRepositoryImpl implements VetRepository {

    private final DynamoDBMapper mapper;

    @Override
    public List<Vet> findAll() {
        // TODO use Spring Data for DynamoDB
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        ScanResultPage<Vet> scan = mapper.scanPage(Vet.class, scanExpression);

        List<Vet> vets = new ArrayList<>(scan.getResults());
        Collections.sort(vets);

        return vets;
    }
}

