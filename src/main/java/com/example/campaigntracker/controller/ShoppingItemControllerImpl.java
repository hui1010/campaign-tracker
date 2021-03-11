package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.ShoppingItemDto;
import com.example.campaigntracker.service.ShoppingItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ShoppingItemControllerImpl implements ShoppingItemController{

    ShoppingItemServiceImpl itemService;

    @Autowired
    public ShoppingItemControllerImpl(ShoppingItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @Override
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ShoppingItemDto>> find() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @Override
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ShoppingItemDto> create(@Valid @RequestBody ShoppingItemDto shoppingItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(shoppingItemDto));
    }

    @Override
    @PutMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ShoppingItemDto> update(@RequestBody ShoppingItemDto shoppingItemDto) {
        return ResponseEntity.ok(itemService.update(shoppingItemDto));
    }

    @Override
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        boolean isRemoved = itemService.delete(id);
        if(!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
