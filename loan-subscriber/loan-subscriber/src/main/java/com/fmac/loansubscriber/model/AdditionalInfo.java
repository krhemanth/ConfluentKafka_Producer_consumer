package com.fmac.loansubscriber.model;

import lombok.Data;

import java.util.List;

@Data
class AdditionalInfo {

    private String loanId;
    private int creditScore;
    private String employmentHistory;
    private String loanPurpose;
    private List<Asset> otherAssets;
}