package net.demo.exploreca.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A Classification of Tours.
 */
@Entity
public class TourPackage implements Serializable {
    @Id
    private String code;

    @Column
    private String name;

    public TourPackage(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TourPackage [code=" + code + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TourPackage other = (TourPackage) obj;
        return Objects.equals(code, other.code) &&
                Objects.equals(name, other.name);
    }

}
