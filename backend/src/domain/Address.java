package domain;

import javax.persistence.*;

/**
 * Created by rfischer on 11.05.16.
 */

@Entity
@Table(name="sdm_address")
public class Address {
    private String street;
    private String nr;
    private String country;
    private String postalcode;
    private String city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Address(String street, String nr, String country, String postalcode, String city, Long id) {
        this.street = street;
        this.nr = nr;
        this.country = country;
        this.postalcode = postalcode;
        this.city = city;
        this.id = id;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
