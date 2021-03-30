package com.posco.mes3.graphqlsample.provider;

import com.posco.mes3.graphqlsample.core.Coffee;
import com.posco.mes3.graphqlsample.core.CoffeeDetails;
import com.posco.mes3.graphqlsample.core.CoffeeRepository;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CoffeeProvider implements CoffeeDetails {

    private final CoffeeRepository coffeeRepository;

    private final AllCoffeesDataFetcher allCoffeesDataFetcher;
    private final CoffeeDataFetcher coffeeDataFetcher;

    @Value("classpath:coffees.graphql")
    Resource resource;

    private GraphQL graphQL;

    public CoffeeProvider(CoffeeRepository coffeeRepository, AllCoffeesDataFetcher allCoffeesDataFetcher, CoffeeDataFetcher coffeeDataFetcher) {
        this.coffeeRepository = coffeeRepository;
        this.allCoffeesDataFetcher = allCoffeesDataFetcher;
        this.coffeeDataFetcher = coffeeDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCoffees", allCoffeesDataFetcher)
                        .dataFetcher("coffee", coffeeDataFetcher))
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Coffee findByCid(String cid) {
        return coffeeRepository.findByCid(cid);
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }
}
