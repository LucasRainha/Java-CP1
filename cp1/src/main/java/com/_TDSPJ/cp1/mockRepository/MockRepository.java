package com._TDSPJ.cp1.mockRepository;
import  com._TDSPJ.cp1.model.Tipo;

import com._TDSPJ.cp1.model.Carro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockRepository {

    private final List<Carro> dataSet = new ArrayList<>();

    public MockRepository (){
        this.dataSet.add( new Carro(1L , "Gol", "Volkswagen", 2021L, 1000L, Tipo.COMBUSTAO, "10000"));
        this.dataSet.add( new Carro(2L , "Seal", "BYD", 2024L, 3000L, Tipo.ELETRICO, "30000"));
        this.dataSet.add( new Carro(3L , "Civic", "Honda", 2022L, 2000L, Tipo.HIBRIDO, "20000"));
    }

    public List<Carro> getAll(){
        return this.dataSet.subList(0, this.dataSet.size());
    }

    public Carro save (Carro carro){
        this.dataSet.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        Long lastID = this.dataSet.get(this.dataSet.size() -1).getId();
        carro.setId(lastID + 1);
        this.dataSet.add(carro);
        return carro;
    }

    public void deleteById(Long id) {
        this.dataSet.removeIf(
                employee ->
                        employee.getId().equals(id));
    }

    public void delete(Carro aCarro) {
        this.dataSet.removeIf(
                employee ->
                        employee.getId().equals(aCarro.getId()));
    }

    public Carro findById(Long id) {
        for(Carro carro : this.getAll()){
            if(carro.getId().equals(id)){
                return carro;
            }
        }
        return null;
    }



}
