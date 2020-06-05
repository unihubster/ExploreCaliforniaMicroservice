package net.demo.exploreca.repositories;

import org.springframework.data.repository.CrudRepository;

import net.demo.exploreca.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {

}
