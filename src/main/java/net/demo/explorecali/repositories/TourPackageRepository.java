package net.demo.explorecali.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import net.demo.explorecali.domain.TourPackage;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);

    // Another way to control following is to use Spring Security
    @Override
    @RestResource(exported = false)
    <S extends TourPackage> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String id);

    @Override
    @RestResource(exported = false)
    void delete(TourPackage tourPackage);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends TourPackage> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
