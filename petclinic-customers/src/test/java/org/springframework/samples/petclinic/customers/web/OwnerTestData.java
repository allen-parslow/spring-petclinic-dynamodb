package org.springframework.samples.petclinic.customers.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.samples.petclinic.customers.model.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.samples.petclinic.api.testing.json.JsonTestUtils.readJson;


@Getter
@NoArgsConstructor
public final class OwnerTestData {
    private List<Owner> items = new ArrayList<>();

    public void add(String firstName, String lastName, String address, String city, String telephone) {
        Owner item = new Owner(UUID.randomUUID().toString());
        item.setFirstName(firstName);
        item.setLastName(lastName);
        item.setAddress(address);
        item.setCity(city);
        item.setTelephone(telephone);

        items.add(item);
    }

    public static List<Owner> list() {
        return readJson("owners-db.json", OwnerTestData.class).getItems();
    }
}
