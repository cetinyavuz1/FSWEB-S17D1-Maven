package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @PostConstruct
    public void loadAll(){
        System.out.println("postcontruct çalıştı.");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1,"maymun"));
    }

    @GetMapping
    public List<Animal> getAnimals(){
        return animals.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id){
        if(id < 0 ){
            System.out.println("Id sıfırdan küçük olamaz");
            return null;
        }
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal){
        this.animals.replace(id, animal);
        return this.animals.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable int id){
        this.animals.remove(id);
    }
}