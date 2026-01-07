package kg.producerservice.service.impl;

import kg.producerservice.dto.LoanRequestDto;
import kg.producerservice.dto.LoanRequestEvent;
import kg.producerservice.enums.LoanState;
import kg.producerservice.model.LoanApplication;
import kg.producerservice.repository.LoanRepository;
import kg.producerservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final KafkaTemplate<String, LoanRequestEvent> kafkaTemplate;

    private static final String TOPIC_REQUESTS = "loan-requests";

    @Override
    public Long createApplication(LoanRequestDto request) {
        LoanApplication loan = LoanApplication.builder()
                .clientName(request.getClientName())
                .amount(request.getAmount())
                .salary(request.getSalary())
                .status(LoanState.IN_PROGRESS)
                .build();

        LoanApplication savedLoan = loanRepository.save(loan);
        log.info("Saved loan application with id: {}", savedLoan.getId());

        LoanRequestEvent loanEvent = LoanRequestEvent.builder()
                .requestId(savedLoan.getId())
                .amount(savedLoan.getAmount())
                .salary(savedLoan.getSalary())
                .build();

        kafkaTemplate.send(TOPIC_REQUESTS, savedLoan.getId().toString(), loanEvent);
        log.info("Event send to kafka with: {}", loanEvent);

        return  savedLoan.getId();
    }

    @Override
    public String getStatus(Long id) {
        return loanRepository.findById(id)
                .map(LoanApplication::getStatus)
                .map(Enum::name)
                .orElse(LoanState.NOT_FOUND.name());
    }
}
