package com._TDSPJ.cp1.controller;


import com._TDSPJ.cp1.model.Carro;
import com._TDSPJ.cp1.service.ServiceCarro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carro")
public class ControllerCarro {

    @Autowired
    private final ServiceCarro service;

    public ControllerCarro(ServiceCarro service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Carro>> getAllCarros(){
        return new ResponseEntity<>(
                this.service.getAll(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> findById(@PathVariable("id") Long id){
        Carro carro = this.service.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro){
        Carro newCarro = this.service.save(carro);
        return new ResponseEntity<>(newCarro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro(@PathVariable Long id, @RequestBody Carro carro){
        Carro dbCarro = this.service.getById(id);
        dbCarro.setMarca(carro.getMarca());
        dbCarro.setModelo(carro.getModelo());
        dbCarro.setTipo(carro.getTipo());
        dbCarro.setAno(carro.getAno());
        dbCarro.setPotencia(carro.getPotencia());
        dbCarro.setPreco(carro.getPreco());
        this.service.update(dbCarro);
        return new ResponseEntity<>(dbCarro, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carro> patchCarro(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Carro updatedCarro = this.service.partialUpdate(id, updates);
        if (updatedCarro != null){
            return new ResponseEntity<>(updatedCarro, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
