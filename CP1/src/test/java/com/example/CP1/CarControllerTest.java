package com.example.CP1;

import com.example.CP1.controllers.CarController;
import com.example.CP1.models.Car;
import com.example.CP1.models.Type;
import com.example.CP1.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void testGet10CarsWithHighestPower() throws Exception {
        List<Car> cars = Arrays.asList(
                new Car(1L, "Gol", "Volkswagen", 2021L, 1000L, Type.COMBUSTION, "10000", "12 km/l"),
                new Car(2L, "Model 3", "Tesla", 2023L, 3000L, Type.ELECTRIC, "30000", "7 km/kWh")
        );

        when(carService.getCarByPower()).thenReturn(cars);

        mockMvc.perform(get("/api/carros/potencia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].model").value("Gol"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].model").value("Model 3"));

        verify(carService, times(1)).getCarByPower();
    }

    @Test
    void testGet10CarsWithHighestEfficiency() throws Exception {
        List<Car> cars = Arrays.asList(
                new Car(1L, "Civic", "Honda", 2022L, 2000L, Type.HYBRID, "20000", "20 km/l"),
                new Car(2L, "Leaf", "Nissan", 2023L, 110L, Type.ELECTRIC, "27000", "6.5 km/kWh")
        );

        when(carService.getCarByEfficiency()).thenReturn(cars);

        mockMvc.perform(get("/api/carros/eficiencia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].model").value("Civic"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].model").value("Leaf"));

        verify(carService, times(1)).getCarByEfficiency();
    }

    @Test
    void testGetAllEletricCars() throws Exception {
        List<Car> cars = Arrays.asList(
                new Car(1L, "Model 3", "Tesla", 2023L, 3000L, Type.ELECTRIC, "30000", "7 km/kWh"),
                new Car(2L, "Leaf", "Nissan", 2023L, 110L, Type.ELECTRIC, "27000", "6.5 km/kWh")
        );

        when(carService.getAllEletricCars()).thenReturn(cars);

        mockMvc.perform(get("/api/carros/eletricos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].model").value("Model 3"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].model").value("Leaf"));

        verify(carService, times(1)).getAllEletricCars();
    }

    @Test
    void testFindACarById() throws Exception {
        Car car = new Car(1L, "Gol", "Volkswagen", 2021L, 1000L, Type.COMBUSTION, "10000", "12 km/l");

        when(carService.getById(1L)).thenReturn(car);

        mockMvc.perform(get("/api/carros/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.model").value("Gol"));

        verify(carService, times(1)).getById(1L);
    }

    @Test
    void testDeleteACarById() throws Exception {

        doNothing().when(carService).deleteById(1L);


        mockMvc.perform(delete("/api/carros/{id}", 1L))
                .andExpect(status().isNoContent());


        verify(carService, times(1)).deleteById(1L);
    }


    @Test
    void testCreateANewCar() throws Exception {
        Car newCar = new Car(1L, "Civic", "Honda", 2022L, 2000L, Type.HYBRID, "20000", "20 km/l");

        when(carService.save(any(Car.class))).thenReturn(newCar);

        mockMvc.perform(post("/api/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\":\"Civic\",\"brand\":\"Honda\",\"year\":2022,\"power\":2000,\"type\":\"HYBRID\",\"price\":\"20000\",\"efficiency\":\"20 km/l\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.model").value("Civic"));

        verify(carService, times(1)).save(any(Car.class));
    }

    @Test
    void testUpdateACar() throws Exception {
        Car car = new Car(1L, "Civic", "Honda", 2022L, 2000L, Type.HYBRID, "20000", "20 km/l");

        when(carService.getById(1L)).thenReturn(car);
        when(carService.update(any(Car.class))).thenReturn(car);

        mockMvc.perform(put("/api/carros/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\":\"Civic\",\"brand\":\"Honda\",\"year\":2022,\"power\":2000,\"type\":\"HYBRID\",\"price\":\"20000\",\"efficiency\":\"20 km/l\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.model").value("Civic"));

        verify(carService, times(1)).update(any(Car.class));
    }
}
