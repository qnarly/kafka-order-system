package kg.consumerservice.dto;

import kg.consumerservice.enums.LoanState;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanResultEvent {
    private Long requestId;
    private LoanState status;
}
