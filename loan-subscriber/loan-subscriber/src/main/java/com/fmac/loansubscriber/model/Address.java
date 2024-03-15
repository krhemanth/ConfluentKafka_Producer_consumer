package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}