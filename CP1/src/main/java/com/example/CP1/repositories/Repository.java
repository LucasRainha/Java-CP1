package com.example.CP1.repositories;

import com.example.CP1.models.Car;
import com.example.CP1.models.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Repository {

    private final List<Car> dataBase= new ArrayList<>();

    public Repository(){
        this.dataBase.add(new Car(1L , "Gol", "Volkswagen", 2021L, 1000L, Type.COMBUSTION, "10000", "12 km/l"));
        this.dataBase.add(new Car(2L , "Seal", "BYD", 2024L, 3000L, Type.ELECTRIC, "30000", "7 km/kWh"));
        this.dataBase.add(new Car(3L , "Civic", "Honda", 2022L, 2000L, Type.HYBRID, "20000", "20 km/l"));
        this.dataBase.add(new Car(4L , "Model 3", "Tesla", 2023L, 258L, Type.ELECTRIC, "35000", "6 km/kWh"));
        this.dataBase.add(new Car(5L , "Corolla", "Toyota", 2021L, 1800L, Type.HYBRID, "22000", "18 km/l"));
        this.dataBase.add(new Car(6L , "Mustang", "Ford", 2020L, 5000L, Type.COMBUSTION, "45000", "7 km/l"));
        this.dataBase.add(new Car(7L , "iX", "BMW", 2023L, 300L, Type.ELECTRIC, "60000", "5.5 km/kWh"));
        this.dataBase.add(new Car(8L , "Tucson", "Hyundai", 2022L, 1600L, Type.HYBRID, "28000", "15 km/l"));
        this.dataBase.add(new Car(9L , "Onix", "Chevrolet", 2021L, 1000L, Type.COMBUSTION, "15000", "13 km/l"));
        this.dataBase.add(new Car(10L , "Leaf", "Nissan", 2023L, 110L, Type.ELECTRIC, "27000", "6.5 km/kWh"));
        this.dataBase.add(new Car(11L , "Fiesta", "Ford", 2020L, 1200L, Type.COMBUSTION, "12000", "14 km/l"));
        this.dataBase.add(new Car(12L , "X5", "BMW", 2022L, 250L, Type.HYBRID, "70000", "12 km/l"));
        this.dataBase.add(new Car(13L , "A4", "Audi", 2021L, 1500L, Type.COMBUSTION, "50000", "10 km/l"));
        this.dataBase.add(new Car(14L , "Leaf", "Nissan", 2022L, 200L, Type.ELECTRIC, "35000", "7 km/kWh"));
        this.dataBase.add(new Car(15L , "Camry", "Toyota", 2023L, 1700L, Type.HYBRID, "33000", "16 km/l"));
        this.dataBase.add(new Car(16L , "Renegade", "Jeep", 2021L, 2200L, Type.COMBUSTION, "35000", "11 km/l"));
        this.dataBase.add(new Car(17L , "Porsche 911", "Porsche", 2020L, 4000L, Type.COMBUSTION, "120000", "8 km/l"));
        this.dataBase.add(new Car(18L , "Outlander", "Mitsubishi", 2023L, 1300L, Type.HYBRID, "40000", "14 km/l"));
        this.dataBase.add(new Car(19L , "EQB", "Mercedes-Benz", 2024L, 300L, Type.ELECTRIC, "85000", "5.2 km/kWh"));
        this.dataBase.add(new Car(20L , "Kona", "Hyundai", 2022L, 1800L, Type.HYBRID, "28000", "17 km/l"));

    }

    public List<Car> getAll(){
        return this.dataBase;
    }


    public List<Car> get10CarsWithHighestPower(){
        return this.dataBase.stream()
                .sorted((c1, c2) -> c2.getPower().compareTo(c1.getPower()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private double parseEconomyValue(String efficiency) {
        if (efficiency == null) return 0.0;
        try {
            String numericPart = efficiency.replaceAll("[^0-9.]", "");
            return Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public List<Car> get10CarsWithHighestEfficiency(){
        return this.dataBase.stream()
                .sorted((c1, c2) -> Double.compare(
                        parseEconomyValue(c2.getEfficiency()),
                        parseEconomyValue(c1.getEfficiency())
                ))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Car> getAllEletricCars(){
        List<Car> eletricCars = new ArrayList<>();
        for(Car car : this.dataBase){
            if(car.getType().equals(Type.ELECTRIC)){
                eletricCars.add(car);
            }
        }
        return eletricCars;
    }

    public Car save (Car carro){
        this.dataBase.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        Long lastID = this.dataBase.get(this.dataBase.size() -1).getId();
        carro.setId(lastID + 1);
        this.dataBase.add(carro);
        return carro;
    }
    public void deleteById(Long id) {
        this.dataBase.removeIf(
                employee ->
                        employee.getId().equals(id));
    }

    public void delete(Car car) {
        this.dataBase.removeIf(
                employee ->
                        employee.getId().equals(car.getId()));

    }

    public Car findById(Long id) {
        for(Car car : this.getAll()){
            if(car.getId().equals(id)){
                return car;
            }
        }
        return null;
    }
}
