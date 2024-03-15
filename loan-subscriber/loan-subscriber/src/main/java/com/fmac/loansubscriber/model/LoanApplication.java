package com.fmac.loansubscriber.model;

import lombok.Data;

import java.util.List;

@Data
public class LoanApplication {

    private LoanDetails loanDetails;
    private AdditionalInfo additionalInfo;
    private ContractDetails contractDetails;
}