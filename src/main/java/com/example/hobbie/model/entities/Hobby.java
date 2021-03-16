package com.example.hobbie.model.entities;

import com.example.hobbie.model.entities.enums.AboTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hobbies")
public class Hobby extends BaseEntity{

    private String name;
    private String description;
    private Category category;
    private String profilePhoto;
    private BusinessOwner businessOwner;



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
}
