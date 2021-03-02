package com.example.campaigntracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CampaignId;

    private String name;
    private String store;
    private double price;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private boolean match;

    public Campaign() {
    }

    public Campaign(String name, String store, double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.match = false;
    }

    public Campaign(String name, String store, double price) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.match = false;
    }

    public int getCampaignId() {
        return CampaignId;
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

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Double.compare(campaign.price, price) == 0 && match == campaign.match && Objects.equals(name, campaign.name) && Objects.equals(store, campaign.store) && Objects.equals(dateBegin, campaign.dateBegin) && Objects.equals(dateEnd, campaign.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, store, price, dateBegin, dateEnd, match);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "CampaignId=" + CampaignId +
                ", name='" + name + '\'' +
                ", store='" + store + '\'' +
                ", price=" + price +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", match=" + match +
                '}';
    }
}
