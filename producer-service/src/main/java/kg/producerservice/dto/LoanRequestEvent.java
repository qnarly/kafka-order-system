package kg.producerservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestEvent {
    private Long requestId;
    private BigDecimal amount;
    private BigDecimal salary;
}
