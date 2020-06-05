package net.demo.explorecali.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.demo.explorecali.domain.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String tourPackageName);

}
