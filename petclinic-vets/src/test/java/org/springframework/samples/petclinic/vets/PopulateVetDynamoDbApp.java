package org.springframework.samples.petclinic.vets;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.petclinic.api.core.AmazonDynamoDBConfig;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetTestData;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Populates test data into the DynamoDb Vets table.
 */
@Slf4j
public class PopulateVetDynamoDbApp {

    private final DynamoDBMapper mapper;

    public PopulateVetDynamoDbApp(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String... args) throws Exception {
        PopulateVetDynamoDbApp scratchpad = new PopulateVetDynamoDbApp(new AmazonDynamoDBConfig().dynamoDBMapper());

        scratchpad.addEntityData();
    }

    void addEntityData() {
        Map<String, Vet> vetMap = VetTestData.list().stream().collect(Collectors.toMap(
                Vet::key,
                it -> it
        ));

        // don't do this on massive production scale data ...
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<Vet> scan = mapper.scan(Vet.class, scanExpression);
        for (Vet vet : scan) {
            vetMap.remove(vet.key());
        }

        log.info("Vets to add: " + vetMap.keySet());
        for (Vet vet : vetMap.values()) {
            mapper.save(vet);
        }
    }
}
