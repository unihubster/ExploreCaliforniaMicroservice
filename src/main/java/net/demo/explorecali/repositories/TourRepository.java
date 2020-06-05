package net.demo.explorecali.repositories;

import org.springframework.data.repository.CrudRepository;

import net.demo.explorecali.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {

}
