package com.posco.mes3.graphqlsample.core;

import graphql.ExecutionResult;
import org.springframework.stereotype.Service;

@Service
public class CoffeeUseCase {

    private final CoffeeDetails coffeeDetails;

    public CoffeeUseCase(CoffeeDetails coffeeDetails) {
        this.coffeeDetails = coffeeDetails;
    }

    public ExecutionResult execute(String query) {
        return coffeeDetails.execute(query);
    }
}
