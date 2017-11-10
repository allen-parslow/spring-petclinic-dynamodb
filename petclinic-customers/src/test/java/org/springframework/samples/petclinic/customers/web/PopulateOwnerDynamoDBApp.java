package org.springframework.samples.petclinic.customers.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.petclinic.api.core.dynamodb.AmazonDynamoDBConfig;
import org.springframework.samples.petclinic.api.core.json.ObjectMapperFactory;
import org.springframework.samples.petclinic.api.testing.dynamodb.DynamoDBDataSeeder;
import org.springframework.samples.petclinic.customers.model.Owner;

/**
 * Populates test data into the DynamoDb Owners table.
 */
@Slf4j
public class PopulateOwnerDynamoDBApp {

    public static void main(String... args) throws Exception {
        DynamoDBDataSeeder seeder = new DynamoDBDataSeeder(new AmazonDynamoDBConfig().dynamoDBMapper());
        seeder.seed(Owner.class, OwnerTestData.list(), Owner::key);
    }

    static void createJson() throws JsonProcessingException {
        OwnerTestData data = new OwnerTestData();
        data.add("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023");
        data.add("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749");
        data.add("Eduardo", "Rodriquez", "2693 Commerce St.", "McFarland", "6085558763");
        data.add("Harold", "Davis", "563 Friendly St.", "Windsor", "6085553198");
        data.add("Peter", "McTavish", "2387 S. Fair Way", "Madison", "6085552765");
        data.add("Jean", "Coleman", "105 N. Lake St.", "Monona", "6085552654");
        data.add("Jeff", "Black", "1450 Oak Blvd.", "Monona", "6085555387");
        data.add("Maria", "Escobito", "345 Maple St.", "Madison", "6085557683");
        data.add("David", "Schroeder", "2749 Blackhawk Trail", "Madison", "6085559435");
        data.add("Carlos", "Estaban", "2335 Independence La.", "Waunakee", "6085555487");
        System.out.println(new ObjectMapperFactory().objectMapper().writeValueAsString(data));
    }
}
