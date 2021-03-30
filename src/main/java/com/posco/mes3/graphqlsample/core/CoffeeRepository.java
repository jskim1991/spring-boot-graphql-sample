package com.posco.mes3.graphqlsample.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, String> {
    Coffee findByCid(String cid);
}
