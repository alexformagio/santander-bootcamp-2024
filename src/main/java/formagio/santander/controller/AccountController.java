package formagio.santander.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import formagio.santander.controller.dto.AccountDTO;
import formagio.santander.controller.dto.CreditDebitDTO;
import formagio.santander.controller.dto.TransferDTO;
import formagio.santander.controller.dto.UserDTO;
import formagio.santander.model.Account;
import formagio.santander.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "RESTful API for Account.")
public class AccountController {
	
	private final AccountService accountservice;

	public AccountController(AccountService accountservice) {
		super();
		this.accountservice = accountservice;
	}
	
    @GetMapping
    @Operation(summary = "Get all accounts", description = "Retrieve a list of all accounts")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<AccountDTO>> findAll() {
        var accounts = accountservice.findAll();
        var accountDTO = accounts.stream().map(AccountDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(accountDTO);
    }
	
    
    @GetMapping("/{number}")
    @Operation(summary = "Get a Account by Number", description = "Retrieve a specific Account based on its Number")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<AccountDTO> findById(@PathVariable String number) {
        var account = accountservice.findByNumber(number);
        return ResponseEntity.ok(new AccountDTO(account));
    }

    @PostMapping("/credit")
    @Operation(summary = "Do a credit in a specified account", description = "Do a credit in a specified account")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Credited successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid data provided")
    })
    public ResponseEntity<AccountDTO> credit(@RequestBody CreditDebitDTO inputDTO) {
    	Account account = accountservice.doCredit(inputDTO);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{number}")
                .buildAndExpand(account.getNumber())
                .toUri();
        return ResponseEntity.created(location).body(new AccountDTO(account));
    }
    

    @PostMapping("/debit")
    @Operation(summary = "Do a debit in a specified account", description = "Do a debit in a specified account")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Credited successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid data provided")
    })
    public ResponseEntity<AccountDTO> debit(@RequestBody CreditDebitDTO inputDTO) {
    	Account account = accountservice.doDebit(inputDTO);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{number}")
                .buildAndExpand(account.getNumber())
                .toUri();
        return ResponseEntity.created(location).body(new AccountDTO(account));
    }
    
    @PostMapping("/transfer")
    @Operation(summary = "Do a transfer between two specified account", description = "Do a transfer between two specified account")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Credited successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid data provided")
    })
    public ResponseEntity<Void> transfer(@RequestBody TransferDTO inputDTO) {
    	accountservice.doTransfer(inputDTO);
    	return ResponseEntity.ok().build();
    }    

}
