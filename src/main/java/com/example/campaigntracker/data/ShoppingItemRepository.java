package com.example.campaigntracker.data;

import com.example.campaigntracker.entity.ShoppingItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingItemRepository extends CrudRepository <ShoppingItem, Integer> {
    List<ShoppingItem> findAll();
    ShoppingItem findByNameIgnoreCase(String name);
}
