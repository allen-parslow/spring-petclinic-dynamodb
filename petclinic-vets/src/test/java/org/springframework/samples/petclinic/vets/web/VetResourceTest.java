package org.springframework.samples.petclinic.vets.web;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.samples.petclinic.vets.model.VetRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VetResourceTest {

    @InjectMocks
    private VetResource resource;

    @Mock
    private VetRepository mockRepository;

    @Test
    public void shouldFindAll() {
        List<Vet> expected = new ArrayList<>();
        when(mockRepository.findAll()).thenReturn(expected);

        List<Vet> results = resource.findAll();
        assertThat(results, sameInstance(expected));
    }
}