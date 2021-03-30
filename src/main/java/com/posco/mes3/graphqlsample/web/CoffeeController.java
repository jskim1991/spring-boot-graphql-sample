package com.posco.mes3.graphqlsample.web;

import com.posco.mes3.graphqlsample.core.Coffee;
import com.posco.mes3.graphqlsample.core.CoffeeRepository;
import com.posco.mes3.graphqlsample.core.CoffeeUseCase;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    CoffeeRepository repo; // for initial data

    private final CoffeeUseCase coffeeUseCase;

    public CoffeeController(CoffeeUseCase coffeeUseCase) {
        this.coffeeUseCase = coffeeUseCase;
    }

    /**
     * Using GraphQL
     */
    @PostMapping
    public ResponseEntity<Object> getAllCoffees (@RequestBody String query) {
        ExecutionResult execute = coffeeUseCase.execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }


    /**
     * For generating initial data
     */
    @PostMapping("/new")
    public void addCoffee(@RequestBody Coffee coffee) {
        repo.save(coffee);
    }

    @GetMapping("/get")
    public List<Coffee> getCoffees() {
        return repo.findAll();
    }
}
