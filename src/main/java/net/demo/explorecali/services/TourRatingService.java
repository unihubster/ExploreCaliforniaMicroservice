package net.demo.explorecali.services;

import org.springframework.stereotype.Service;

import net.demo.explorecali.domain.Tour;
import net.demo.explorecali.domain.TourRating;
import net.demo.explorecali.domain.TourRatingPk;
import net.demo.explorecali.dto.RatingDto;
import net.demo.explorecali.repositories.TourRatingRepository;

@Service
public class TourRatingService {
    private TourRatingRepository tourRatingRepository;
    private TourService tourService;

    public TourRatingService(TourRatingRepository tourRatingRepository, TourService tourService) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourService = tourService;
    }

    /**
     * Create a Tour Rating.
     *
     * @param tourId    tour identifier
     * @param ratingDto rating data transfer object
     */
    public void create(long tourId, RatingDto ratingDto) {
        Tour tour = tourService.verifyTour(tourId);
        tourRatingRepository.save(
                new TourRating(
                        new TourRatingPk(tour, ratingDto.getCustomerId()),
                        ratingDto.getScore(),
                        ratingDto.getComment()));

    }

}
