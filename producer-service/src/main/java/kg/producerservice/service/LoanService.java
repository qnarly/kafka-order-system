package kg.producerservice.service;

import kg.producerservice.dto.LoanRequestDto;
import kg.producerservice.enums.LoanState;

public interface LoanService {

    Long createApplication(LoanRequestDto request);

    LoanState getStatus(Long id);
}
