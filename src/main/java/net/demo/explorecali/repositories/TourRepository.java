package net.demo.explorecali.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.demo.explorecali.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {

    Page<Tour> findByTourPackageCode(String code, Pageable pageable);

}
