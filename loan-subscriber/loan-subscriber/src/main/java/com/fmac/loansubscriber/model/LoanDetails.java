package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class LoanDetails {

    private String midasId;
    private String loanType;
    private Borrower borrower;
    private double loanAmount;
    private double interestRate;
    private int termInYears;
    private double ltvRatio;
    private PropertyDetails propertyDetails;
    private EmploymentDetails employmentDetails;
}
