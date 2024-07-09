package formagio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByAccountNumber(String number);

	boolean existsByCardNumber(String number);

}
