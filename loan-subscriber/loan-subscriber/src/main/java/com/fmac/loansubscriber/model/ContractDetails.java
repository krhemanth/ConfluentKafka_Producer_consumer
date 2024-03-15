package com.fmac.loansubscriber.model;

import lombok.Data;

@Data
class ContractDetails {

    private String contractId;
    private String interestType;
    private double monthlyPayment;
    private String dueDate;
}
