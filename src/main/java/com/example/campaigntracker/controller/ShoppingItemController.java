package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.ShoppingItemDto;
import org.springframework.http.ResponseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ShoppingItemController {
    ResponseEntity<List<ShoppingItemDto>> findAll(final String type, final String value);
    ResponseEntity<ShoppingItemDto> create(ShoppingItemDto shoppingItemDto);
    ResponseEntity<ShoppingItemDto> update(ShoppingItemDto shoppingItemDto);
    ResponseEntity<Integer> delete(Integer id);
}
