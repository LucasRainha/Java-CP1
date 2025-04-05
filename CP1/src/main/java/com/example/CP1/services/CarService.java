package com.example.CP1.services;

import com.example.CP1.models.Car;
import com.example.CP1.repositories.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
@AllArgsConstructor
public class CarService {

    @Autowired
    private Repository repository;

    public List<Car> getCarByPower(){
        return this.repository.get10CarsWithHighestPower();
    }
    public List<Car> getCarByEfficiency(){
        return this.repository.get10CarsWithHighestEfficiency();
    }
    public List<Car> getAllEletricCars(){
        return this.repository.getAllEletricCars();
    }
    public Car getById(Long id){
        return this.repository.findById(id);
    }
    public Car save(Car car){
        return this.repository.save(car);
    }
    public Car update(Car car){
        return this.repository.save(car);
    }
    public void deleteById(Long id){
        this.repository.deleteById(id);
    }
    public void delete(Car car) {
        this.repository.delete(car);
    }
}
