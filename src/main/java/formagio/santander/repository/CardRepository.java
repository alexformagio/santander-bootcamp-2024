package formagio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
