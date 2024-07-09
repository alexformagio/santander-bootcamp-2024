package formagio.santander.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formagio.santander.controller.dto.HistoryDTO;
import formagio.santander.service.impl.AccountStatementServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/history")
@Tag(name = "AccountStatement Controller", description = "RESTful API for AccountStatement.")
public class AccountStatementController {
	
    private final AccountStatementServiceImpl accountStatementService;
	
    public AccountStatementController(AccountStatementServiceImpl accountStatementService) {
		super();
		this.accountStatementService = accountStatementService;
	}



	@GetMapping("/{number}/{dataini}")
    @Operation(summary = "Get a Account and history by Number", description = "Retrieve a specific Account and history based on its Number")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<HistoryDTO> findByNumber(@PathVariable String number,@PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataini) {
       HistoryDTO history = accountStatementService.getHistory(number,dataini);
       return ResponseEntity.ok(history);
    }	

}
