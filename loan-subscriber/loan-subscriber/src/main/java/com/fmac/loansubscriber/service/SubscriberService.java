package com.fmac.loansubscriber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@Slf4j
public class SubscriberService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    @PostConstruct
    public void initIndexes() {
        IndexOperations indexOps = mongoTemplate.indexOps("current_loan");

        IndexDefinition midasIdIndex = new Index().on("midasId", Sort.Direction.ASC).named("midasId_index");
        IndexDefinition loanIdIndex = new Index().on("loanId", Sort.Direction.ASC).named("loanId_index");
        IndexDefinition contractIdIndex = new Index().on("contractId", Sort.Direction.ASC).named("contractId_index");

        indexOps.ensureIndex(midasIdIndex);
        indexOps.ensureIndex(loanIdIndex);
        indexOps.ensureIndex(contractIdIndex);
    }


    @KafkaListener(topics = "loan-status", groupId = "loan-group")
    public void loanDetail(@Payload String loanContent) {
        try {
            log.info("Loan Content: [{}]", loanContent);
            BasicDBObject savedDocument = saveLoanJson(loanContent);
            // Acknowledge the successful insert
            if (savedDocument != null) {
                log.info("Loan JSON saved successfully in Mongo with Document ID: " + savedDocument.getObjectId("_id"));
            } else {
                log.error("Failed to save Loan JSON.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public BasicDBObject saveLoanJson(String json) throws JsonProcessingException {
        try {
            JsonNode loanDataNode = objectMapper.readTree(json);
            String midasId = loanDataNode.path("loanDetails").path("midasId").asText();
            String loanId = loanDataNode.path("additionalInfo").path("loanId").asText();
            String contractId = loanDataNode.path("contractDetails").path("contractId").asText();

            BasicDBObject loanDataDocument = new BasicDBObject();
            loanDataDocument.put("midasId", midasId);
            loanDataDocument.put("loanId", loanId);
            loanDataDocument.put("contractId", contractId);
            BasicDBObject parsedJson = BasicDBObject.parse(json);
            loanDataDocument.putAll((Map) parsedJson);
            loanDataDocument.put("RawLoanData", json);
            return mongoTemplate.save(loanDataDocument, "current_loan");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
