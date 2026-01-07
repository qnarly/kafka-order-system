package kg.producerservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDto {
    private String clientName;
    private BigDecimal amount;
    private BigDecimal salary;
}
