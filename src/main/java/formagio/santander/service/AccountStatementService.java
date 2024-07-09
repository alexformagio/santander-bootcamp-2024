package formagio.santander.service;

import java.time.LocalDate;
import java.util.List;

import formagio.santander.controller.dto.HistoryDTO;
import formagio.santander.model.Account;
import formagio.santander.model.AccountStatement;

public interface AccountStatementService {
	List<AccountStatement> findByNumber(Account acc);
	List<AccountStatement> findByNumberAndPeriod(Account acc, LocalDate dataIni, LocalDate dataFim);
	HistoryDTO getHistory(String number,LocalDate dataIni);
	
}
