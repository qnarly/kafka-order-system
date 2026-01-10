package kg.consumerservice.service;

import kg.consumerservice.dto.LoanRequestEvent;
import kg.consumerservice.dto.LoanResultEvent;
import kg.consumerservice.enums.LoanState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoringSystem {

    private final KafkaTemplate<String, LoanResultEvent> kafkaTemplate;

    @KafkaListener(topics = "loan-requests", groupId = "scoring-group")
    private void consume(LoanRequestEvent event) throws InterruptedException {
        log.info("Received loan request event: {}", event);

        log.info("Checking credit history...");
        Thread.sleep(5000);

        LoanResultEvent resultEvent = new LoanResultEvent();
        resultEvent.setRequestId(event.getRequestId());


        if (checkMaxAmount(event)) {
            resultEvent.setStatus(LoanState.APPROVED);
            log.info("Loan with requestId: {} has been approved", event.getRequestId());
        } else if (!checkMaxAmount(event)) {
            resultEvent.setStatus(LoanState.REJECTED);
            log.info("Loan with requestId: {} has been rejected", event.getRequestId());
        } else {
            resultEvent.setStatus(LoanState.NOT_FOUND);
            log.info("Loan with requestId: {} not found", event.getRequestId());
        }

        kafkaTemplate.send("loan-results", event.getRequestId().toString(), resultEvent);
        log.info("result sends to topic loan-results: {}", resultEvent);
    }


    private boolean checkMaxAmount(LoanRequestEvent event) {
        return event.getAmount().compareTo(event.getSalary().multiply(BigDecimal.valueOf(5))) > 0;
    }
}
