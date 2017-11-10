package org.springframework.samples.petclinic.api.core.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Still considering Spring Data
 */
@RequiredArgsConstructor
public abstract class AbstractDynamoDBRepository<T, ID> {

    private final Class<T> entityClass;
    private final DynamoDBMapper mapper;

    protected abstract T toId(ID id);

    public void update(T entity) {
        mapper.save(entity);
    }

    public T read(ID id) {
        return mapper.load(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    protected T load(T object) {
        Class<T> objectClass = (Class) object.getClass();
        DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<T>()
                .withHashKeyValues(object);
        return mapper.load(objectClass, queryExpression);
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByIndex(String  index, T object) {
        Class<T> objectClass = (Class) object.getClass();
        DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<T>()
                .withIndexName(index)
                .withConsistentRead(false)
                .withHashKeyValues(object);
        return mapper.query(objectClass, queryExpression);
    }

    public List<T> findOnePage() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        ScanResultPage<T> scan = mapper.scanPage(entityClass, scanExpression);

        return new ArrayList<>(scan.getResults());
    }

    protected DynamoDBMapper getMapper() {
        return mapper;
    }
}
