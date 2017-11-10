package org.springframework.samples.petclinic.vets.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.api.core.json.ObjectMapperFactory;

import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VetRepositoryImplTest {

    @InjectMocks
    private VetRepositoryImpl repo;

    @Mock
    private DynamoDBMapper mockMapper;

    @Test
    public void shouldTransformIdToIdObject() throws Exception {
        Vet item = repo.toId("123");
        String text = new ObjectMapperFactory().objectMapper().writeValueAsString(item);
        assertThat(text, is("{\"id\":\"123\"}"));
    }
}