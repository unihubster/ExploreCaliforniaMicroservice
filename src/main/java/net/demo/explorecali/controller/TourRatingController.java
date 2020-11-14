package net.demo.explorecali.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
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
     * Lookup a the Ratings for a tour.
     *
     * @param tourId Tour Identifier
     * @return All Tour Ratings as RatingDto's
     */
    @GetMapping
    public List<RatingDto> getAllRaringsForTour(
            @PathVariable(value = "tourId") long tourId) {
        return tourRatingService.getAllRaringsForTour(tourId);
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
