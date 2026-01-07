package kg.producerservice.service;

import kg.producerservice.dto.LoanRequestDto;

public interface LoanService {

    Long createApplication(LoanRequestDto request);

    String getStatus(Long id);
}
