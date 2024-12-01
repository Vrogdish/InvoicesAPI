package com.Invoices.myApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Invoices.myApi.entities.ProfileEntity;
import com.Invoices.myApi.errors.RessourceNotFoundException;
import com.Invoices.myApi.models.Profile;
import com.Invoices.myApi.models.ProfileCreate;
import com.Invoices.myApi.repositories.ProfileRepository;
import com.Invoices.myApi.security.AuthService;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AuthService authService;

    public Profile getProfile() {
        ProfileEntity profileEntity = profileRepository.findByUid(authService.getUserId());
        if (profileEntity == null || profileEntity.getUid() != authService.getUserId()) {
            throw new RessourceNotFoundException("Profile not found");
        }
        return new Profile(
                profileEntity.getId(),
                profileEntity.getUid(),
                profileEntity.getPhotoUrl(),
                profileEntity.getCompany(),
                profileEntity.getCivility(),
                profileEntity.getFirstname(),
                profileEntity.getLastname(),
                profileEntity.getEmail(),
                profileEntity.getPhone(),
                profileEntity.getStreetNumber(),
                profileEntity.getRoute(),
                profileEntity.getLocality(),
                profileEntity.getPostalCode(),
                profileEntity.getCountry(),
                profileEntity.getPlaceId());
    }

    public Profile createProfile(ProfileCreate profile) {
        ProfileEntity profileEntity = new ProfileEntity(
                authService.getUserId(),
                profile.photoUrl(),
                profile.company(),
                profile.civility(),
                profile.firstname(),
                profile.lastname(),
                profile.email(),
                profile.phone(),
                profile.streetNumber(),
                profile.route(),
                profile.locality(),
                profile.postalCode(),
                profile.country(),
                profile.placeId()
                );
        profileRepository.save(profileEntity);

        return new Profile(
                profileEntity.getId(),
                profileEntity.getUid(),
                profileEntity.getPhotoUrl(),
                profileEntity.getCompany(),
                profileEntity.getCivility(),
                profileEntity.getFirstname(),
                profileEntity.getLastname(),
                profileEntity.getEmail(),
                profileEntity.getPhone(),
                profileEntity.getStreetNumber(),
                profileEntity.getRoute(),
                profileEntity.getLocality(),
                profileEntity.getPostalCode(),
                profileEntity.getCountry(),
                profileEntity.getPlaceId());
    }

    public Profile updateProfile(ProfileCreate profile) {
        ProfileEntity profileEntity = profileRepository.findByUid(authService.getUserId());
        if (profileEntity == null || profileEntity.getUid() != authService.getUserId()) {
            throw new RessourceNotFoundException("Profile not found");
        }
        profileEntity.setPhotoUrl(profile.photoUrl());
        profileEntity.setCompany(profile.company());
        profileEntity.setCivility(profile.civility());
        profileEntity.setFirstname(profile.firstname());
        profileEntity.setLastname(profile.lastname());
        profileEntity.setEmail(profile.email());
        profileEntity.setPhone(profile.phone());
        profileEntity.setStreetNumber(profile.streetNumber());
        profileEntity.setRoute(profile.route());
        profileEntity.setLocality(profile.locality());
        profileEntity.setPostalCode(profile.postalCode());
        profileEntity.setCountry(profile.country());
        profileEntity.setPlaceId(profile.placeId());

        profileRepository.save(profileEntity);

        return new Profile(
                profileEntity.getId(),
                profileEntity.getUid(),
                profileEntity.getPhotoUrl(),
                profileEntity.getCompany(),
                profileEntity.getCivility(),
                profileEntity.getFirstname(),
                profileEntity.getLastname(),
                profileEntity.getEmail(),
                profileEntity.getPhone(),
                profileEntity.getStreetNumber(),
                profileEntity.getRoute(),
                profileEntity.getLocality(),
                profileEntity.getPostalCode(),
                profileEntity.getCountry(),
                profileEntity.getPlaceId());
    }

}
