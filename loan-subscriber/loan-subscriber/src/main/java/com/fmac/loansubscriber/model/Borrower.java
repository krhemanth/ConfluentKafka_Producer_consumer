package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class Borrower {

    private String name;
    private String dob;
    private String ssn;
    private Address address;
}