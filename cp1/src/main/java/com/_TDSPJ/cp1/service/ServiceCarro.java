package com._TDSPJ.cp1.service;

import com._TDSPJ.cp1.mockRepository.MockRepository;
import com._TDSPJ.cp1.model.Carro;
import com._TDSPJ.cp1.model.Tipo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor

public class ServiceCarro {

    @Autowired
    private MockRepository repository;

    public List<Carro> getAll(){
        return this.repository.getAll();
    }

    public Carro getById(Long id){
        return this.repository.findById(id);
    }

    public Carro save(Carro carro){
        return this.repository.save(carro);
    }

    public Carro update(Carro carro){
        return this.repository.save(carro);
    }

    public void deleteById(Long id){
        this.repository.deleteById(id);
    }

    public void delete(Carro carro) {
        this.repository.delete(carro);
    }

    public Carro partialUpdate(Long id, Map<String, Object> updates) {
        Carro carro = this.repository.findById(id);
        for(Map.Entry<String, Object> entry : updates.entrySet()){
            switch (entry.getKey()){
                case "id": carro.setId(Long.parseLong(entry.getValue().toString())); break;
                case "marca": carro.setMarca(entry.getValue().toString()); break;
                case "modelo": carro.setModelo(entry.getValue().toString()); break;
                case "preco": carro.setPreco(entry.getValue().toString()); break;
                case "ano": carro.setAno(Long.parseLong(entry.getValue().toString())); break;
                case "potencia": carro.setPotencia(Long.parseLong(entry.getValue().toString())); break;
                case "tipo": carro.setTipo(Tipo.valueOf(entry.getValue().toString())); break;

            }
        }
        return this.repository.save(carro);
    }
}

