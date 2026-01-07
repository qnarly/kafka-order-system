package kg.producerservice.service.impl;

import kg.producerservice.dto.LoanRequestDto;
import kg.producerservice.dto.LoanRequestEvent;
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

    @Override
    public Long createApplication(LoanRequestDto request) {
        return 0L;
    }

    @Override
    public String getStatus(Long id) {
        return "";
    }
}
