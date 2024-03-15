package com.fmac.loanpublisher.controller;

import com.fmac.loanpublisher.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/producer-api")
public class PublishController {

    @Autowired
    private PublishService publishService;

    @PostMapping("/publish")
    public ResponseEntity<?> publishlLoan(@RequestBody String message){
        try{
            publishService.sendLoanToTopic(message);
            return ResponseEntity.ok("Loan Details Published Successfully!!!");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }
}
