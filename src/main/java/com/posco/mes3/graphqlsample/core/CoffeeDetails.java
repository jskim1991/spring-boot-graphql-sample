package com.posco.mes3.graphqlsample.core;

import graphql.ExecutionResult;

import java.util.List;

public interface CoffeeDetails {
    ExecutionResult execute(String query);
}
