package com.example.campaigntracker.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String store;

    @Column(nullable = false)
    private double price;

    @Column(nullable = true)
    private LocalDate dateBegin;

    @Column(nullable = true)
    private LocalDate dateEnd;

    private boolean meet;

    public Campaign() {
    }

    public Campaign(String name, String store, double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.meet = false;
    }

    public Campaign(String name, String store, double price, LocalDate dateBegin, LocalDate dateEnd, boolean match) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.meet = match;
    }

    public Campaign(String name, String store, double price) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.meet = false;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isMeet() {
        return meet;
    }

    public void setMeet(boolean meet) {
        this.meet = meet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Double.compare(campaign.price, price) == 0 && meet == campaign.meet && Objects.equals(name, campaign.name) && Objects.equals(store, campaign.store) && Objects.equals(dateBegin, campaign.dateBegin) && Objects.equals(dateEnd, campaign.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, store, price, dateBegin, dateEnd, meet);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId=" + campaignId +
                ", name='" + name + '\'' +
                ", store='" + store + '\'' +
                ", price=" + price +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", meet=" + meet +
                '}';
    }
}
