package com.example.campaigntracker.service;

import com.example.campaigntracker.dto.ShoppingItemDto;

import java.util.List;

public interface ShoppingItemService {
    List<ShoppingItemDto> findAll();
    ShoppingItemDto create(ShoppingItemDto shoppingItemDto);
    ShoppingItemDto update(ShoppingItemDto shoppingItemDto);
    boolean delete(int shoppingItemId);
}
