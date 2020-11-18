package net.demo.explorecali.services;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.demo.explorecali.domain.Tour;
import net.demo.explorecali.domain.TourPackage;
import net.demo.explorecali.repositories.TourPackageRepository;
import net.demo.explorecali.repositories.TourRepository;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    /**
     * Create a new Tour Object and persist it to the Database
     *
     * @param title           Title of the tour
     * @param tourPackageName tour Package of the tour
     * @param details         Extra details about the tour
     * @return Tour
     */
    public Tour createTour(String title, String tourPackageName, Map<String, String> details) {
        TourPackage tourPackage = tourPackageRepository
                .findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageName));
        return tourRepository.save(new Tour(title, tourPackage, details));
    }

    /**
     * Calculate the number of Tours in the Database.
     *
     * @return the total.
     */
    public long getTotalNumberOfTours() {
        return tourRepository.count();
    }

    /**
     * Verify and return the Tour given a tourId.
     *
     * @param tourId tour identifier
     * @throws NoSuchElementException if no Tour found.
     */
    public void verifyTour(String tourId) throws NoSuchElementException {
        if (!tourRepository.existsById(tourId)) {
            throw new NoSuchElementException("Tour does not exist " + tourId);
        }
    }
}
