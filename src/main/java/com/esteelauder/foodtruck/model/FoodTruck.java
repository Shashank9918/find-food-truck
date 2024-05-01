package com.esteelauder.foodtruck.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodTruck {
    private String locationId;
    private String applicant;
    private String facilityType;

    private String foodItems;
    private double latitude;
    private double longitude;

}
