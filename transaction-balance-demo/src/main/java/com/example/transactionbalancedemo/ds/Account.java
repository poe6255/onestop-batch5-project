package com.example.transactionbalancedemo.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Account {
    private Integer id;
    private String name;
    private int amount;
}
