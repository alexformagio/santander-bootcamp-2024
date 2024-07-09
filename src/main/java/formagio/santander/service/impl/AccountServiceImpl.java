package formagio.santander.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import formagio.santander.controller.dto.CreditDebitDTO;
import formagio.santander.controller.dto.TransferDTO;
import formagio.santander.model.Account;
import formagio.santander.model.AccountStatement;
import formagio.santander.model.enums.StatementType;
import formagio.santander.repository.AccountRepository;
import formagio.santander.repository.AccountStatementRepository;
import formagio.santander.service.AccountService;
import formagio.santander.service.exception.BusinessException;
import formagio.santander.service.exception.NotFoundException;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;
	private final AccountStatementRepository accountStatementRepository;
	
	

	public AccountServiceImpl(AccountRepository accountRepository,
			AccountStatementRepository accountStatementRepository) {
		super();
		this.accountRepository = accountRepository;
		this.accountStatementRepository = accountStatementRepository;
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findById(Long id) {
		return accountRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Account findByNumber(String number) {
		return accountRepository.findByNumber(number).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public Account doCredit(CreditDebitDTO inputed) {
		var account = findByNumber(inputed.account());
		BigDecimal saldoAnt = account.getBalance();
		BigDecimal saldoAtual = saldoAnt.add(inputed.amount());
		account.setBalance(saldoAtual);
		accountRepository.save(account);
		AccountStatement as = new AccountStatement();
		as.setAccount(account);
		as.setCurrentBalance(saldoAtual);
		as.setData(LocalDate.now());
		as.setDescription(inputed.description());
		as.setPreviousBalance(saldoAnt);
		as.setStatementType(StatementType.CREDIT);
		as.setValue(inputed.amount());
		accountStatementRepository.save(as);
		return account;
	}

	@Override
	@Transactional
	public Account doDebit(CreditDebitDTO inputed) {
		var account = findByNumber(inputed.account());
		if(account.getBalance().compareTo(inputed.amount()) == -1) {
			throw new BusinessException("Insuficient Balance to perform withdraw");
		}
		BigDecimal saldoAnt = account.getBalance();
		BigDecimal saldoAtual = saldoAnt.subtract(inputed.amount());
		account.setBalance(saldoAtual);
		accountRepository.save(account);
		AccountStatement as = new AccountStatement();
		as.setAccount(account);
		as.setCurrentBalance(saldoAtual);
		as.setData(LocalDate.now());
		as.setDescription(inputed.description());
		as.setPreviousBalance(saldoAnt);
		as.setStatementType(StatementType.DEBIT);
		as.setValue(inputed.amount());
		accountStatementRepository.save(as);
		return account;
	}

	@Override
	@Transactional
	public void doTransfer(TransferDTO inputed) {
		doDebit(new CreditDebitDTO(inputed.OriginAccount(), "Transfer to account: " + inputed.DestinationAccount(), inputed.amount()));
		doCredit(new CreditDebitDTO(inputed.DestinationAccount(), "Transfer from account: " + inputed.OriginAccount(), inputed.amount()));
	}

}
