package com.example.campaigntracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shoppingItemId;

    private String name;

    public ShoppingItem() {
    }

    public ShoppingItem(String name) {
        this.name = name;
    }

    public int getShoppingItemId() {
        return shoppingItemId;
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
        ShoppingItem that = (ShoppingItem) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "shoppingItemId=" + shoppingItemId +
                ", name='" + name + '\'' +
                '}';
    }
}
