package net.demo.exploreca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.demo.exploreca.domain.TourPackage;
import net.demo.exploreca.repositories.TourPackageRepository;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        if (tourPackageRepository.existsById(code)) {
            return null; // TODO replace "return null" if it's possible with
            // return tourPackageRepository.findById(code);
        } else {
            return tourPackageRepository.save(new TourPackage(code, name));
        }
    }

    public Iterable<TourPackage> getAllTourPackagies() {
        return tourPackageRepository.findAll();
    }

    public long getTotalNumberOfTourPackagies() {
        return tourPackageRepository.count();
    }
}
