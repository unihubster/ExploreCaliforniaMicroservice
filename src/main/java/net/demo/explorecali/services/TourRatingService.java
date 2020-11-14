package net.demo.explorecali.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
     * Lookup a page of Ratings for a tour.
     *
     * @param tourId   Tour Identifier
     * @param pageable paging details
     * @return Requested page of Tour Ratings as RatingDto's
     */
    public Page<RatingDto> getPageableRaringsForTour(long tourId, Pageable pageable) {
        tourService.verifyTour(tourId);
        Page<TourRating> ratings = tourRatingRepository.findByPkTourId(tourId, pageable);
        return new PageImpl<>(
                ratings.get().map(RatingDto::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements());
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

    /**
     * Update score and comment of a Tour Rating
     *
     * @param tourId    tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
    public RatingDto updateWithPut(long tourId, RatingDto ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());
        return new RatingDto(tourRatingRepository.save(rating));
    }

    /**
     * Update score or comment of a Tour Rating
     *
     * @param tourId    tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
    public RatingDto updateWithPatch(long tourId, RatingDto ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getScore() != null) {
            rating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            rating.setComment(ratingDto.getComment());
        }
        return new RatingDto(tourRatingRepository.save(rating));
    }

    /**
     * Delete a Rating of a tour made by a customer
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     */
    public void delete(long tourId, long customerId) {
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
    private TourRating verifyTourRating(long tourId, long customerId) throws NoSuchElementException {
        return tourRatingRepository
                .findByPkTourIdAndPkCustomerId(tourId, customerId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Tour-Rating pair for request %d for customer %d", tourId, customerId)));
    }
}
