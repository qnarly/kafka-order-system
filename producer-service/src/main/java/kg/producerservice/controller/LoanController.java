package kg.producerservice.controller;

import kg.producerservice.dto.LoanRequestDto;
import kg.producerservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Long> applyForLoan(@RequestBody LoanRequestDto loanRequestDto) {
        Long loanId = loanService.createApplication(loanRequestDto);
        return ResponseEntity.ok(loanId);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id){
        String status = loanService.getStatus(id);
        return ResponseEntity.ok(status);
    }

}
