package org.sai.donate.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "donation_items")
//public class DonationItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String itemName;
//    private String category; // clothes, food, etc.
//    private String description;
//    private Integer quantity;
//    private String donorName;
//    private String donorEmail;
//    private String donorPhone;
//    private String imagePath;
//    private String pickupAddress;
//    private LocalDateTime donationDate;
//    private Boolean isClaimed = false;
//
//    // Constructors, getters, and setters
//    public DonationItem() {}
//
//    public DonationItem(String itemName, String category, String description, Integer quantity,
//                        String donorName, String donorEmail, String donorPhone, String imagePath,
//                        String pickupAddress) {
//        this.itemName = itemName;
//        this.category = category;
//        this.description = description;
//        this.quantity = quantity;
//        this.donorName = donorName;
//        this.donorEmail = donorEmail;
//        this.donorPhone = donorPhone;
//        this.imagePath = imagePath;
//        this.pickupAddress = pickupAddress;
//        this.donationDate = LocalDateTime.now();
//        this.isClaimed = false;
//    }
//
//    // Getters and setters for all fields
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getItemName() { return itemName; }
//    public void setItemName(String itemName) { this.itemName = itemName; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//    public String getDonorName() { return donorName; }
//    public void setDonorName(String donorName) { this.donorName = donorName; }
//
//    public String getDonorEmail() { return donorEmail; }
//    public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }
//
//    public String getDonorPhone() { return donorPhone; }
//    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }
//
//    public String getImagePath() { return imagePath; }
//    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
//
//    public String getPickupAddress() { return pickupAddress; }
//    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }
//
//    public LocalDateTime getDonationDate() { return donationDate; }
//    public void setDonationDate(LocalDateTime donationDate) { this.donationDate = donationDate; }
//
//    public Boolean getIsClaimed() { return isClaimed; }
//    public void setIsClaimed(Boolean isClaimed) { this.isClaimed = isClaimed; }
//}











//package com.donation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "donation_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String category; // clothes, food, etc.

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String donorName;

    @Column(nullable = false)
    private String donorEmail;

    private String donorPhone;

    private String imagePath;

    @Column(nullable = false)
    private String pickupAddress;

    @Column(nullable = false)
    private LocalDateTime donationDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isClaimed = false;

    // Custom constructor without ID and with default values
    public DonationItem(String itemName, String category, String description, Integer quantity,
                        String donorName, String donorEmail, String donorPhone, String imagePath,
                        String pickupAddress) {
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorPhone = donorPhone;
        this.imagePath = imagePath;
        this.pickupAddress = pickupAddress;
        this.donationDate = LocalDateTime.now();
        this.isClaimed = false;
    }
}