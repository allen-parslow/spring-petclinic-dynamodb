package org.springframework.samples.petclinic.vets.web;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.samples.petclinic.vets.model.VetRepositoryImpl;
import org.springframework.samples.petclinic.vets.model.VetTestData;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        when(mockRepository.findOnePage()).thenReturn(expected);

        List<Vet> results = resource.findAll();
        assertThat(results, sameInstance(expected));
    }

//    @Test
//    public void shouldReturnAllVetsSortedByLastName() {
//        ScanResultPage<Vet> scan = new ScanResultPage<>();
//        scan.setResults(VetTestData.list());
//        when(mockMapper.scanPage(eq(Vet.class), any(DynamoDBScanExpression.class))).thenReturn(scan);
//
//        List<Vet> vets = repo.findOnePage();
//
//        Vet vet = vets.get(0);
//        assertThat(vet.toString(), is("James Carter"));
//        assertThat(vet.getId(), is("34b85402-0ec8-4edb-9a37-b5e046e7b41c"));
//        assertThat(vet.getSpecialties().toString(), is("[none]"));
//        assertThat(vets.get(1).toString(), is("Linda Douglas"));
//        assertThat(vets.get(1).getSpecialties().toString(), is("[dentistry, surgery]"));
//        assertThat(vets.get(2).toString(), is("Hudson Jenkins"));
//        assertThat(vets.get(3).toString(), is("Sharon Jenkins"));
//        assertThat(vets.get(4).toString(), is("Helen Leary"));
//        assertThat(vets.get(5).toString(), is("Rafael Ortega"));
//        assertThat(vets.get(6).toString(), is("Henry Stevens"));
//        assertThat(vets.size(), is(7));
//    }
}