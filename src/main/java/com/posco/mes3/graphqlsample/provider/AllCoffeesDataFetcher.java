package com.posco.mes3.graphqlsample.provider;

import com.posco.mes3.graphqlsample.core.Coffee;
import com.posco.mes3.graphqlsample.core.CoffeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllCoffeesDataFetcher implements DataFetcher<List<Coffee>> {

    private final CoffeeRepository coffeeRepository;

    public AllCoffeesDataFetcher(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public List<Coffee> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return coffeeRepository.findAll();
    }
}
