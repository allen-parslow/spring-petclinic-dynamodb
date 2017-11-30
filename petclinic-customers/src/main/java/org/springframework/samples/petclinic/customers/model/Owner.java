/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.customers.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Maciej Szarlinski
 * @author Allen Parslow
 */
@DynamoDBTable(tableName="owners")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Owner {
    public static final String BY_LAST_NAME = "lastName-GUID-index";

    @DynamoDBHashKey(attributeName="GUID")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = BY_LAST_NAME, attributeName = "GUID")
    private String id;
    private String firstName;
    @DynamoDBIndexHashKey(globalSecondaryIndexName = BY_LAST_NAME)
    private String lastName;
    private String address;
    private String city;
    private String state;

    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @JsonIgnore
    @DynamoDBIgnore
    private List<Pet> pets;

    public Owner(String id) {
        this.id = id;
    }

    @JsonIgnore
    @DynamoDBIgnore
    protected List<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        return this.pets;
    }

    @JsonIgnore
    public void addPet(Pet pet) {
        getPetsInternal().add(pet);
    }

    @JsonIgnore
    @DynamoDBIgnore
    public String key() {
        return firstName + ' ' + lastName;
    }

}
