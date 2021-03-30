package com.posco.mes3.graphqlsample.provider;

import com.posco.mes3.graphqlsample.core.Coffee;
import com.posco.mes3.graphqlsample.core.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CoffeeDataFetcher implements DataFetcher<Coffee> {

    private final CoffeeRepository coffeeRepository;

    public CoffeeDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public Coffee get(DataFetchingEnvironment dataFetchingEnvironment) {
        String cid = dataFetchingEnvironment.getArgument("id");
        return coffeeRepository.findByCid(cid);
    }
}
