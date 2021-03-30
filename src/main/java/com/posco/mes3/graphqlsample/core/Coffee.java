package com.posco.mes3.graphqlsample.core;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table
@Entity
public class Coffee {
    @Id
    private String cid;
    private String name;

    public Coffee(String cid, String name) {
        this.cid = cid;
        this.name = name;
    }
}
