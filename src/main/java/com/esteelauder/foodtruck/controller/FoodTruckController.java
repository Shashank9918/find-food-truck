package com.esteelauder.foodtruck.controller;


import com.esteelauder.foodtruck.model.FoodTruck;
import com.esteelauder.foodtruck.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/trucks")
public class FoodTruckController {
    @Autowired
    private FoodTruckService foodTruckService;

    @GetMapping
    public List<FoodTruck> getAllTrucks() {
        try {
            return foodTruckService.getAllFoodTruck();
        } catch(IOException e) {
            return null;
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<FoodTruck>> getTruckByFoodType(@RequestParam String type) {
        try {
            List<FoodTruck> trucks = foodTruckService.getFoodTrucksByType(type);
            if(trucks.isEmpty())
                    return ResponseEntity.noContent().build();
            return ResponseEntity.ok(trucks);
        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
