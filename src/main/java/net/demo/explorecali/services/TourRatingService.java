package net.demo.explorecali.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.demo.explorecali.domain.TourRating;
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
     * @param tourId     tour identifier
     * @param tourRating rating data transfer object
     */
    public void create(String tourId, TourRating tourRating) {
        tourService.verifyTour(tourId);
        tourRatingRepository.save(new TourRating(tourId,
                                                 tourRating.getCustomerId(),
                                                 tourRating.getScore(),
                                                 tourRating.getComment()));
    }

    /**
     * Lookup a the Ratings for a tour.
     *
     * @param tourId Tour Identifier
     * @return All Tour Ratings as RatingDto's
     */
    public List<TourRating> getAllRaringsForTour(String tourId) {
        return tourRatingRepository.findByTourId(tourId);
    }

    /**
     * Lookup a page of Ratings for a tour.
     *
     * @param tourId   Tour Identifier
     * @param pageable paging details
     * @return Requested page of Tour Ratings as RatingDto's
     */
    public Page<TourRating> getPageableRaringsForTour(String tourId, Pageable pageable) {
        return tourRatingRepository.findByTourId(tourId, pageable);
    }

    /**
     * Calculate the average Score of a Tour.
     *
     * @param tourId tour identifier
     * @return Tuple of "average" and the average value.
     */
    public Map<String, Double> getAverage(String tourId) {
        tourService.verifyTour(tourId);
        return Map.of("average", tourRatingRepository
                .findByTourId(tourId)
                .stream()
                .mapToInt(TourRating::getScore)
                .average()
                .orElseThrow(() -> new NoSuchElementException("Tour has no ratings")));
    }

    /**
     * Update score and comment of a Tour Rating
     *
     * @param tourId     tour identifier
     * @param tourRating rating Data Transfer Object
     * @return The modified TourRating as DTO.
     */
    public TourRating updateWithPut(String tourId, TourRating tourRating) {
        TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
        rating.setScore(tourRating.getScore());
        rating.setComment(tourRating.getComment());
        return tourRatingRepository.save(rating);
    }

    /**
     * Update score or comment of a Tour Rating
     *
     * @param tourId     tour identifier
     * @param tourRating rating Data Transfer Object
     * @return The modified TourRating as DTO.
     */
    public TourRating updateWithPatch(String tourId, TourRating tourRating) {
        TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
        if (tourRating.getScore() != null) {
            rating.setScore(tourRating.getScore());
        }
        if (tourRating.getComment() != null) {
            rating.setComment(tourRating.getComment());
        }
        return tourRatingRepository.save(rating);
    }

    /**
     * Delete a Rating of a tour made by a customer
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     */
    public void delete(String tourId, long customerId) {
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(rating);
    }

    /**
     * Verify and return the TourRating for a particular tourId and Customer
     * 
     * @param tourId     tour identifier
     * @param customerId customer identifier
     * @return the found TourRating
     * @throws NoSuchElementException if no TourRating found
     */
    private TourRating verifyTourRating(String tourId, long customerId) throws NoSuchElementException {
        return tourRatingRepository
                .findByTourIdAndCustomerId(tourId, customerId)
                .orElseThrow(
                        () -> new NoSuchElementException(
                                                         String.format(
                                                                 "Tour-Rating pair for request %d for customer %d",
                                                                 tourId, customerId)));
    }
}
