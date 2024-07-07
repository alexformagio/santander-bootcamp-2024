package formagio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
