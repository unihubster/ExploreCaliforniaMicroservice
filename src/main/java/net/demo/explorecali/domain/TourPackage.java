package net.demo.explorecali.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Classification of Tours.
 */
@Document
public class TourPackage {

    @Id
    private String code;

    private String name;

    protected TourPackage() {}

    public TourPackage(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return code;
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
