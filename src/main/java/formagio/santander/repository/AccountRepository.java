package formagio.santander.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByNumber(String number);

}
