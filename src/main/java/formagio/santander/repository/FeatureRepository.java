package formagio.santander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formagio.santander.model.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
