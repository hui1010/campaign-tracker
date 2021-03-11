package com.example.campaigntracker.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class CampaignDto {
    @Null(message = "Campaign id should not be present.")
    private Integer campaignId;

    @NotNull(message = "Name of campaign is needed.")
    @Size(min = 1)
    private String name;

    @NotNull(message = "Store is needed.")
    @Size(min = 1)
    private String store;

    @NotNull(message = "Amount of items is needed")
    @Positive
    private Integer amount = 1;

    @NotNull(message = "Price is needed")
    @PositiveOrZero
    private Double price;

    private LocalDate dateBegin;

    private LocalDate dateEnd;

    public CampaignDto() {
    }

    public CampaignDto(String name, String store, Double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public CampaignDto(String name, String store, Double price) {
        this.name = name;
        this.store = store;
        this.amount = 1;
        this.price = price;
    }

    public CampaignDto(String name, String store, Integer amount, Double price) {
        this.name = name;
        this.store = store;
        this.amount = amount;
        this.price = price;
    }

    public CampaignDto( Integer campaignId, String name, String store, Integer amount, Double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.campaignId = campaignId;
        this.name = name;
        this.store = store;
        this.amount = amount;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public CampaignDto(String name, String store, Integer amount, Double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.name = name;
        this.store = store;
        this.amount = amount;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignDto that = (CampaignDto) o;
        return Objects.equals(name, that.name) && Objects.equals(store, that.store) && Objects.equals(amount, that.amount) && Objects.equals(price, that.price) && Objects.equals(dateBegin, that.dateBegin) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, store, amount, price, dateBegin, dateEnd);
    }

    @Override
    public String toString() {
        return "CampaignDto{" +
                "campaignId=" + campaignId +
                ", name='" + name + '\'' +
                ", store='" + store + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
