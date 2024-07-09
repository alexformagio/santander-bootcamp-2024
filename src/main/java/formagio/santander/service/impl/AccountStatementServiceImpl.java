package formagio.santander.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import formagio.santander.controller.dto.AccountDTO;
import formagio.santander.controller.dto.AccountStatementDTO;
import formagio.santander.controller.dto.HistoryDTO;
import formagio.santander.model.Account;
import formagio.santander.model.AccountStatement;
import formagio.santander.repository.AccountRepository;
import formagio.santander.repository.AccountStatementRepository;
import formagio.santander.service.AccountStatementService;
import formagio.santander.service.exception.BusinessException;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {
	
	private final AccountStatementRepository accountStatementrepository;
	private final AccountRepository accountRepository;


	public AccountStatementServiceImpl(AccountStatementRepository accountStatementrepository,
			AccountRepository accountRepository) {
		super();
		this.accountStatementrepository = accountStatementrepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<AccountStatement> findByNumber(Account acc) {
		return accountStatementrepository.findByAccountOrderByIdAsc(acc);
	}

	@Override
	public List<AccountStatement> findByNumberAndPeriod(Account acc, LocalDate dataIni, LocalDate dataFim) {
		if(dataFim.isBefore(dataIni)) {
			throw new BusinessException("Initial date is after last date");
		}
		
		return accountStatementrepository.findByAccountAndDataBetweenOrderByIdAsc(acc,dataIni,dataFim);
	}

	public HistoryDTO getHistory(String number, LocalDate dataIni) {
	  	Account account = accountRepository.findByNumber(number).get();
	  	List<AccountStatement> listAS;
	  	if(dataIni == null) {
	  		listAS = findByNumber(account);
	  	}else {
	  		listAS = findByNumberAndPeriod(account, dataIni, LocalDate.now());
	  	}
	  	List<AccountStatementDTO> asDTO = listAS.stream().map(AccountStatementDTO::new).collect(Collectors.toList());
	  	HistoryDTO history = new HistoryDTO(new AccountDTO(account), asDTO);
	return history;
	}


}
