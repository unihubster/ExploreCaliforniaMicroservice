package net.demo.explorecali.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Tour Rating Primary Key containing a Tour and a Customer Identifier
 */
@Embeddable
public class TourRatingPk implements Serializable {

    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false, nullable = false)
    private Long customerId;

    public TourRatingPk() {}

    /**
     * Fully initialize a Tour Rating Pk
     *
     * @param tour       the tour.
     * @param customerId the customer identifier.
     */
    public TourRatingPk(Tour tour, Long customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }

    public Tour getTour() {
        return tour;
    }

    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TourRatingPk that = (TourRatingPk) o;

        if (!tour.equals(that.tour))
            return false;
        return customerId.equals(that.customerId);

    }

    @Override
    public int hashCode() {
        int result = tour.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TourRatingPk{" +
                "tour=" + tour +
                ", customerId=" + customerId +
                '}';
    }
}
