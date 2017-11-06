package org.springframework.samples.petclinic.vets.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.springframework.samples.petclinic.vets.util.FileTestUtils.readJson;

@Getter
@NoArgsConstructor
public final class VetTestData {
    private List<Vet> vets = new ArrayList<>();

    public void add(String firstName, String lastName, String... specialties) {
        Vet vet = new Vet(UUID.randomUUID().toString());
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialties(asList(specialties));

        vets.add(vet);
    }

    public static List<Vet> list() {
        return readJson("vets-db.json", VetTestData.class).getVets();
    }
}
