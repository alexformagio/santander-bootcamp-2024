package formagio.santander.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import formagio.santander.model.AccountStatement;
import formagio.santander.model.enums.StatementType;

public record AccountStatementDTO(Long id,
		String accountNumber, 
		LocalDate data, 
		BigDecimal previousBalance, 
		BigDecimal value,
		BigDecimal currentBalance,
		String description,
		StatementType type) {

	public AccountStatementDTO(AccountStatement model) {
		this(model.getId(), model.getAccount().getNumber(), model.getData(), model.getPreviousBalance(),
			 model.getValue(), model.getCurrentBalance(), model.getDescription(), model.getStatementType()
	);}
	
}
