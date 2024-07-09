package formagio.santander.service;

import java.util.List;

import formagio.santander.controller.dto.CreditDebitDTO;
import formagio.santander.controller.dto.TransferDTO;
import formagio.santander.model.Account;

public interface AccountService {
	List<Account> findAll();
	Account findById(Long id);
	Account findByNumber(String number);
	Account doCredit(CreditDebitDTO inputed);
	Account doDebit(CreditDebitDTO inputed);
	void doTransfer(TransferDTO inputed);
}
