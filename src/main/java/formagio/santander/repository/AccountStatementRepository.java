package formagio.santander.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.Account;
import formagio.santander.model.AccountStatement;

public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {

	List<AccountStatement> findByAccountOrderByIdAsc(Account ac);

	List<AccountStatement> findByAccountAndDataBetweenOrderByIdAsc(Account ac, LocalDate dataIni, LocalDate dataFim);

}
