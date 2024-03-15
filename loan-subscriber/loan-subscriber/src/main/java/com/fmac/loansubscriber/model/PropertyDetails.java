package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class PropertyDetails {

    private Address address;
    private double appraisalValue;
    private String propertyType;
}