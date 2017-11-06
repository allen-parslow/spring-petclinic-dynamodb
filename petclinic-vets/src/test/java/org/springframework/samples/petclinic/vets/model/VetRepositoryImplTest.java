package org.springframework.samples.petclinic.vets.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void shouldReturnAllVetsSortedByLastName() {
        ScanResultPage<Vet> scan = new ScanResultPage<>();
        scan.setResults(VetTestData.list());
        when(mockMapper.scanPage(eq(Vet.class), any(DynamoDBScanExpression.class))).thenReturn(scan);

        List<Vet> vets = repo.findAll();

        Vet vet = vets.get(0);
        assertThat(vet.toString(), is("James Carter"));
        assertThat(vet.getId(), is("34b85402-0ec8-4edb-9a37-b5e046e7b41c"));
        assertThat(vet.getSpecialties().toString(), is("[none]"));
        assertThat(vets.get(1).toString(), is("Linda Douglas"));
        assertThat(vets.get(1).getSpecialties().toString(), is("[dentistry, surgery]"));
        assertThat(vets.get(2).toString(), is("Hudson Jenkins"));
        assertThat(vets.get(3).toString(), is("Sharon Jenkins"));
        assertThat(vets.get(4).toString(), is("Helen Leary"));
        assertThat(vets.get(5).toString(), is("Rafael Ortega"));
        assertThat(vets.get(6).toString(), is("Henry Stevens"));
        assertThat(vets.size(), is(7));
    }
}