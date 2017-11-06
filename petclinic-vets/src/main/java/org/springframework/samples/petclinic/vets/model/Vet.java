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
package org.springframework.samples.petclinic.vets.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.ObjectUtils.compare;

/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 * @author Maciej Szarlinski
 *
 * @author Allen Parslow
 */
@Getter
@Setter
@DynamoDBTable(tableName="vets")
@NoArgsConstructor
public class Vet implements Comparable<Vet> {

    @DynamoDBHashKey(attributeName="GUID")
    private String id;

    private String firstName;

    private String lastName;

    private List<String> specialties;

    @Override
    public String toString() {
        return key();
    }

    @JsonIgnore
    @DynamoDBIgnore
    public String key() {
        return firstName + ' ' + lastName;
    }

    @Override
    public int compareTo(Vet other) {
        if (other == null) {
            return -1;
        }
        int compare = compare(getLastName(), other.getLastName());
        if (compare == 0) {
            compare = compare(getFirstName(), other.getFirstName());
        }
        return compare;
    }
}
