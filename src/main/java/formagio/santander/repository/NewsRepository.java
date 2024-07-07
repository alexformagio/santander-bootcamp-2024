package formagio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}
