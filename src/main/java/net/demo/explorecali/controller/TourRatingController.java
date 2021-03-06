package net.demo.explorecali.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.demo.explorecali.dto.RatingDto;
import net.demo.explorecali.services.TourRatingService;

/**
 * Tour Rating Controller
 */
@RestController
@RequestMapping(path = "/api/v1.0/tours/{tourId}/ratings")
public class TourRatingController {
    TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }

    /**
     * Create a Tour Rating.
     *
     * @param tourId    tour identifier
     * @param ratingDto rating data transfer object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(
            @PathVariable(value = "tourId") long tourId,
            @RequestBody @Validated RatingDto ratingDto) {
        tourRatingService.create(tourId, ratingDto);
    }

    /**
     * Lookup a page of Ratings for a tour.
     *
     * @param tourId   Tour Identifier
     * @param pageable paging details
     * @return Requested page of Tour Ratings as RatingDto's
     */
    @GetMapping
    public Page<RatingDto> getRaringsForTour(
            @PathVariable(value = "tourId") long tourId,
            Pageable pageable) {
        return tourRatingService.getPageableRaringsForTour(tourId, pageable);
    }

    /**
     * Calculate the average Score of a Tour.
     *
     * @param tourId tour identifier
     * @return Tuple of "average" and the average value.
     */
    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(
            @PathVariable(value = "tourId") long tourId) {
        return tourRatingService.getAverage(tourId);
    }

    /**
     * Update score and comment of a Tour Rating
     *
     * @param tourId    tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
    @PutMapping
    public RatingDto updateWithPut(
            @PathVariable(value = "tourId") long tourId,
            @RequestBody @Validated RatingDto ratingDto) {
        return tourRatingService.updateWithPut(tourId, ratingDto);
    }

    /**
     * Update score or comment of a Tour Rating
     *
     * @param tourId    tour identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
    @PatchMapping
    public RatingDto updateWithPatch(
            @PathVariable(value = "tourId") long tourId,
            @RequestBody @Validated RatingDto ratingDto) {
        return tourRatingService.updateWithPatch(tourId, ratingDto);
    }

    /**
     * Delete a Rating of a tour made by a customer
     *
     * @param tourId     tour identifier
     * @param customerId customer identifier
     */
    @DeleteMapping(path = "/{customerId}")
    public void delete(
            @PathVariable(value = "tourId") long tourId,
            @PathVariable(value = "customerId") long customerId) {
        tourRatingService.delete(tourId, customerId);
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElement(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
