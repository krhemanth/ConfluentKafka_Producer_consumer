package com.fmac.loanpublisher.service;

import com.fmac.loanpublisher.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PublishService {

    @Autowired
    private final KafkaTemplate<String, Object> createKafkaTemplate;

    public PublishService(KafkaTemplate<String, Object> createKafkaTemplate) {
        this.createKafkaTemplate = createKafkaTemplate;
    }

    public boolean sendLoanToTopic(String loanDetails){
        final CompletableFuture<SendResult<String, Object>> future = createKafkaTemplate.send(AppConstant.LOAN_STATUS, UUID.randomUUID().toString(), loanDetails);
        future.whenComplete((result,ex)->{
            if(ex==null){
                log.info("Sent message=[{}] with offset=[{}] to partition=[{}]", loanDetails, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            } else{
                log.error("Unable to send message=[{}] due to: [{}]",loanDetails, ex.getMessage());
            }
        });
        return true;
    }

}
