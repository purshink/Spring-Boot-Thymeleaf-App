package com.example.hobbie.model.entities;

import com.example.hobbie.model.entities.enums.AboTypeEnum;
import com.example.hobbie.model.entities.enums.LocationEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "hobbies")
public class Hobby extends BaseEntity{

    private String name;
    private String description;
    private Category category;
    private String profilePhoto;
    private BusinessOwner businessOwner;
    private BigDecimal price;
    private Location location;
    //TODO HOBBY ADDRESS
    //TODO  HOBBY ENTRY DURTION


    public Hobby() {
    }



    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    @Transient()
    public String getProfilePhotoImagePath() {
        if (profilePhoto == null || id == null) return null;

        return "/hobby-photos/" + id + "/" + profilePhoto;
    }

    public void setPhotos(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @ManyToOne
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
