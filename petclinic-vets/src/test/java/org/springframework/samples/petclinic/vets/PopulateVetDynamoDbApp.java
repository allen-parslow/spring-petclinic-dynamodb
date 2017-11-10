package org.springframework.samples.petclinic.vets;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.petclinic.api.core.dynamodb.AmazonDynamoDBConfig;
import org.springframework.samples.petclinic.api.testing.dynamodb.DynamoDBDataSeeder;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.samples.petclinic.vets.model.VetRepositoryImpl;
import org.springframework.samples.petclinic.vets.model.VetTestData;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Populates test data into the DynamoDb Vets table.
 */
@Slf4j
public class PopulateVetDynamoDbApp {

    public static void main(String... args) throws Exception {
        DynamoDBMapper mapper = new AmazonDynamoDBConfig().dynamoDBMapper();
        DynamoDBDataSeeder seeder = new DynamoDBDataSeeder(mapper);
        seeder.seed(Vet.class, VetTestData.list(), Vet::key);


        VetRepository repository = new VetRepositoryImpl(mapper);
        Vet read = repository.read("34b85402-0ec8-4edb-9a37-b5e046e7b41c");
        read.toString();
    }
}
