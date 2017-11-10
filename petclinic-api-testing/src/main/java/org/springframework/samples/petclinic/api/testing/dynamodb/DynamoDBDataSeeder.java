package org.springframework.samples.petclinic.api.testing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * WARNING: Don't use this class on large tables!!!!
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DynamoDBDataSeeder {

    private final DynamoDBMapper mapper;

    /**
     * WARNING: Don't use this method on large tables!!!!
     */
    public <T, K> void seed(Class<T> entityClass, List<T> items, Function<? super T, K> keyMapper) {
        Map<K, T> itemMap = items.stream().collect(Collectors.toMap(
                keyMapper,
                it -> it
        ));

        // don"t do this on massive production scale data ...
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        PaginatedScanList<T> scan = mapper.scan(entityClass, scanExpression);
        for (T item : scan) {
            K key = keyMapper.apply(item);
            itemMap.remove(key);
        }

        log.info("Items to add: " + itemMap.keySet());
        for (T item : itemMap.values()) {
            mapper.save(item);
        }
    }
}
