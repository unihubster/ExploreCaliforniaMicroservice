package net.demo.exploreca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.demo.exploreca.domain.Difficulty;
import net.demo.exploreca.domain.Region;
import net.demo.exploreca.domain.Tour;
import net.demo.exploreca.domain.TourPackage;
import net.demo.exploreca.repositories.TourPackageRepository;
import net.demo.exploreca.repositories.TourRepository;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(
            String title, String description, String blurb, Integer price, String duration,
            String bullets, String keywords, String tourPackageName, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository
                .findById(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageName));
        return tourRepository.save(
                new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty,
                        region));
    }

    public long getTotalNumberOfTours() {
        return tourRepository.count();
    }
}
