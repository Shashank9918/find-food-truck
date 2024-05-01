package com.esteelauder.foodtruck.service;

import com.esteelauder.foodtruck.model.FoodTruck;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodTruckService {
    private static final String FILE_PATH_CSV = "Mobile_Food_Facility_Permit.csv";

    public List<FoodTruck> getAllFoodTruck() throws IOException {
        System.out.println(parseCSV());
        return parseCSV();
    }

    public List<FoodTruck> getFoodTrucksByType(String foodType) throws IOException{
        List<FoodTruck> allTrucks = parseCSV();

        List<FoodTruck> filteredTrucks = new ArrayList<>();
        for(FoodTruck truck : allTrucks) {
            if(truck.getFoodItems().toLowerCase().contains(foodType.toLowerCase().trim())) {
                System.out.println(truck);
                filteredTrucks.add(truck);
            }
        }
        return filteredTrucks;
    }

    private List<FoodTruck> parseCSV() throws IOException{
        List<FoodTruck> trucks = new ArrayList<>();
        try (CSVParser csvParser = new CSVParser(new InputStreamReader(new ClassPathResource(FILE_PATH_CSV).getInputStream()), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for(CSVRecord csvRecord : csvParser) {
                FoodTruck truck = new FoodTruck();
                truck.setLocationId(csvRecord.get("locationid"));
                truck.setApplicant(csvRecord.get("Applicant"));
                truck.setFacilityType(csvRecord.get("FacilityType"));
                truck.setFoodItems(csvRecord.get("FoodItems"));
                truck.setLatitude(Double.parseDouble(csvRecord.get("Latitude")));
                truck.setLongitude(Double.parseDouble(csvRecord.get("Longitude")));
                trucks.add(truck);
            }
        }
        return trucks;
    }
}
