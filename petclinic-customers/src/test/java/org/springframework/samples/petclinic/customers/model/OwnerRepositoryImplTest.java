package org.springframework.samples.petclinic.customers.model;

import org.junit.Test;
import org.springframework.samples.petclinic.api.core.json.ObjectMapperFactory;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OwnerRepositoryImplTest {
    private OwnerRepositoryImpl repo = new MockOwnerRepositoryImpl();

    @Test
    public void shouldTransformIdToIdObject() throws Exception {
        Owner item = repo.toId("123");
        String text = new ObjectMapperFactory().objectMapper().writeValueAsString(item);
        assertThat(text, is("{\"id\":\"123\"}"));
    }

    @Test
    public void shouldFindByLastNameUsingIndex() throws Exception {
        List<Owner> results = repo.findByLastName("Jones");

        Owner result = results.get(0);
        String text = new ObjectMapperFactory().objectMapper().writeValueAsString(result);
        assertThat(text, is("{\"lastName\":\"Jones\"}"));
        assertThat(results.size(), is(1));
    }

    class MockOwnerRepositoryImpl extends OwnerRepositoryImpl {
        public MockOwnerRepositoryImpl() {
            super(null);
        }

        @Override
        protected List<Owner> findByIndex(String index, Owner object) {
            return singletonList(object);
        }
    }
}