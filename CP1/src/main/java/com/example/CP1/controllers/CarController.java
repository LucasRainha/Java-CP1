package com.example.CP1.controllers;


import com.example.CP1.models.Car;
import com.example.CP1.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarController {

    @Autowired
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/potencia")
    public ResponseEntity<List<Car>> get10CarsWithHighestPower(){
        return new ResponseEntity<>(
                this.service.getCarByPower(), HttpStatus.OK
        );
    }

    @GetMapping("/eficiencia")
    public ResponseEntity<List<Car>> get10CarsWithHighestEfficiency(){
        return new ResponseEntity<>(
                this.service.getCarByEfficiency(), HttpStatus.OK
        );
    }

    @GetMapping("/eletricos")
    public ResponseEntity<List<Car>> getAllEletricCars(){
        return new ResponseEntity<>(
                this.service.getAllEletricCars(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findACarById(@PathVariable("id") Long id){
        Car car = this.service.getById(id);
        if(car == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteACarById(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Car> createANewCar(@RequestBody Car car){
        Car newCar = this.service.save(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateACar(@PathVariable Long id, @RequestBody Car car){
        Car dbCar = this.service.getById(id);
        dbCar.setBrand(car.getBrand());
        dbCar.setModel(car.getModel());
        dbCar.setType(car.getType());
        dbCar.setYear(car.getYear());
        dbCar.setPower(car.getPower());
        dbCar.setPrice(car.getPrice());
        dbCar.setEfficiency(car.getEfficiency());
        this.service.update(dbCar);
        return new ResponseEntity<>(dbCar, HttpStatus.OK);
    }

}
