package com.example.campaigntracker.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class ShoppingItemDto {
    @Null(message = "Shopping item id should not be present")
    private Integer shoppingItemId;

    @NotBlank(message = "Name of shopping item is needed.")
    private String name;

    public ShoppingItemDto() {
    }

    public ShoppingItemDto(String name) {
        this.name = name;
    }

    public ShoppingItemDto(Integer shoppingItemId, String name) {
        this.shoppingItemId = shoppingItemId;
        this.name = name;
    }

    public Integer getShoppingItemId() {
        return shoppingItemId;
    }

    public void setShoppingItemId(Integer shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItemDto that = (ShoppingItemDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShoppingItemDto{" +
                "shoppingItemId=" + shoppingItemId +
                ", name='" + name + '\'' +
                '}';
    }
}
