package com.Invoices.myApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Invoices.myApi.models.Profile;
import com.Invoices.myApi.models.ProfileCreate;
import com.Invoices.myApi.services.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/profile")
@Tag(name = "Profile")

public class ProfileController {
    @Autowired
    ProfileService profileService;

    @Operation(summary = "Get profile", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Return user profile", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Profile.class)) })
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "404", description = "Profile not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @GetMapping("/")
    public ResponseEntity<Profile> getProfile() {
        Profile profile = profileService.getProfile();
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Create profile", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Profile created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Profile.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @PostMapping("/")
    public ResponseEntity<Profile> createProduct(@RequestBody @Valid ProfileCreate profile) {
        Profile createdProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @Operation(summary = "Update profile", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Profile updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Profile.class)) })
    @ApiResponse(responseCode = "400", description = "Bad request", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "404", description = "Profile not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @ApiResponse(responseCode = "500", description = "Internal server error", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    @PutMapping("/")
    public ResponseEntity<Profile> updateProduct(@RequestBody @Valid ProfileCreate profile) {
        Profile updatedProfile = profileService.updateProfile(profile);
        return ResponseEntity.ok(updatedProfile);
    }

}
