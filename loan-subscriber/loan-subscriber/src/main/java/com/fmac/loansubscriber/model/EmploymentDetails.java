package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class EmploymentDetails {

    private String employer;
    private double income;
    private int employmentDurationMonths;
}