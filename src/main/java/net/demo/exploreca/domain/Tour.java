package net.demo.exploreca.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The Tour contains all attributes of an Explore California Tour
 */
@Entity
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String blurb;

    @Column
    private Integer price;

    @Column
    private String duration;

    @Column(length = 2000)
    private String bullets;

    @Column
    private String keywords;

    @ManyToOne
    private TourPackage tourPackage;

    @Column
    private Difficulty difficulty;

    @Column
    private Region region;

    protected Tour() {}

    public Tour(Long id, String title, String description, String blurb, Integer price, String duration,
            String bullets, String keywords, TourPackage tourPackage, Difficulty difficulty, Region region) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.blurb = blurb;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.keywords = keywords;
        this.tourPackage = tourPackage;
        this.difficulty = difficulty;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBullets() {
        return bullets;
    }

    public void setBullets(String bullets) {
        this.bullets = bullets;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Tour [" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", blurb=" + blurb +
                ", price=" + price +
                ", duration=" + duration +
                ", bullets=" + bullets +
                ", keywords=" + keywords +
                ", tourPackage=" + tourPackage +
                ", difficulty=" + difficulty +
                ", region=" + region + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(blurb, bullets, description, difficulty, duration, id, keywords, price, region, title,
                tourPackage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Tour other = (Tour) obj;
        return Objects.equals(blurb, other.blurb) &&
                Objects.equals(bullets, other.bullets) &&
                Objects.equals(description, other.description) &&
                difficulty == other.difficulty &&
                Objects.equals(duration, other.duration) &&
                Objects.equals(id, other.id) &&
                Objects.equals(keywords, other.keywords) &&
                Objects.equals(price, other.price) &&
                region == other.region &&
                Objects.equals(title, other.title) &&
                Objects.equals(tourPackage, other.tourPackage);
    }

}
