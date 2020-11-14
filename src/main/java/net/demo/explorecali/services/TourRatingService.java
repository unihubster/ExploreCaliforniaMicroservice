package net.demo.explorecali.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.demo.explorecali.domain.Tour;
import net.demo.explorecali.domain.TourRating;
import net.demo.explorecali.domain.TourRatingPk;
import net.demo.explorecali.dto.RatingDto;
import net.demo.explorecali.repositories.TourRatingRepository;

@Service
public class TourRatingService {
    private final TourRatingRepository tourRatingRepository;
    private final TourService tourService;

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

    /**
     * Lookup a the Ratings for a tour.
     *
     * @param tourId Tour Identifier
     * @return All Tour Ratings as RatingDto's
     */
    public List<RatingDto> getAllRaringsForTour(long tourId) {
        tourService.verifyTour(tourId);
        return tourRatingRepository
                .findByPkTourId(tourId)
                .stream()
                .map(RatingDto::new)
                .collect(Collectors.toList());
    }

    /**
     * Calculate the average Score of a Tour.
     *
     * @param tourId tour identifier
     * @return Tuple of "average" and the average value.
     */
    public Map<String, Double> getAverage(long tourId) {
        tourService.verifyTour(tourId);
        return Map.of("average", tourRatingRepository
                .findByPkTourId(tourId)
                .stream()
                .mapToInt(TourRating::getScore)
                .average()
                .orElseThrow(() -> new NoSuchElementException("Tour has no ratings")));
    }

}
