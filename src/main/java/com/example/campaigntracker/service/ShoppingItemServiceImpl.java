package com.example.campaigntracker.service;
import com.example.campaigntracker.data.ShoppingItemRepository;
import com.example.campaigntracker.dto.ShoppingItemDto;
import com.example.campaigntracker.entity.ShoppingItem;
import com.example.campaigntracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Configurable
public class ShoppingItemServiceImpl implements ShoppingItemService {

    ShoppingItemRepository shoppingItemRepository;

    @Autowired
    public ShoppingItemServiceImpl(ShoppingItemRepository shoppingItemRepository) {
        this.shoppingItemRepository = shoppingItemRepository;
    }

    protected List<ShoppingItemDto> getShoppingItemDtos(List<ShoppingItem> shoppingItems) {
        List<ShoppingItemDto> result = new ArrayList<>();
        for (ShoppingItem s : shoppingItems){
            ShoppingItemDto shoppingItemDto = getShoppingItemDto(s);
            result.add(shoppingItemDto);
        }
        return result;
    }

    protected ShoppingItemDto getShoppingItemDto(ShoppingItem item) {
        return new ShoppingItemDto(item.getShoppingItemId(), item.getName());
    }

    protected ShoppingItem getShoppingItem(ShoppingItemDto shoppingItemDto){
        ShoppingItem item = new ShoppingItem(shoppingItemDto.getName());
        return item;
    }

    @Override
    public List<ShoppingItemDto> findAll() {
        List<ShoppingItem> foundItems = shoppingItemRepository.findAll();
        return getShoppingItemDtos(foundItems);
    }

    @Override
    @Transactional
    public ShoppingItemDto create(ShoppingItemDto shoppingItemDto) throws RuntimeException {
        if(shoppingItemRepository.findByNameIgnoreCase(shoppingItemDto.getName()) != null)
            throw new RuntimeException("Item already exists");
        ShoppingItem item = new ShoppingItem(shoppingItemDto.getName());
        return getShoppingItemDto(shoppingItemRepository.save(item));
    }

    @Override
    @Transactional
    public ShoppingItemDto update(ShoppingItemDto shoppingItemDto) throws RuntimeException {
        if (!shoppingItemRepository.existsById(shoppingItemDto.getShoppingItemId()))
            throw new RuntimeException("Item does not exist, please create first");
        ShoppingItem item = shoppingItemRepository.findById(shoppingItemDto.getShoppingItemId()).orElseThrow(
                () -> new RuntimeException("Cannot find the user.")
        );
        if (!item.getName().equalsIgnoreCase(shoppingItemDto.getName()))
            item.setName(shoppingItemDto.getName());

        return getShoppingItemDto(shoppingItemRepository.save(item));
    }

    @Override
    @Transactional
    public boolean delete(int id) throws ResourceNotFoundException {
        boolean deleted = false;
        if (!shoppingItemRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Cannot find any loan with book id: " + id);
        }
        if(shoppingItemRepository.existsById(id)) {
            shoppingItemRepository.delete(shoppingItemRepository.findById(id).get());
            deleted = true;
        }
        return deleted;
    }
}
