package com.example.campaigntracker.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class CampaignDto {
    @Null(message = "Campaign id should not be present.")
    private Integer CampaignId;

    @NotBlank(message = "Name of campaign is needed.")
    @Size(min = 1)
    private String name;

    @NotBlank(message = "Store is needed.")
    @Size(min = 1)
    private String store;

    @NotBlank(message = "Price is needed")
    @PositiveOrZero
    private Double price;

    private LocalDate dateBegin;

    private LocalDate dateEnd;

    private Boolean match;

    public CampaignDto() {
    }

    public CampaignDto(String name, String store, Double price, LocalDate dateBegin, LocalDate dateEnd) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.match = false;
    }

    public CampaignDto(String name, String store, Double price) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.match = false;
    }

    public CampaignDto(Integer campaignId, String name, String store, Double price, LocalDate dateBegin, LocalDate dateEnd) {
        CampaignId = campaignId;
        this.name = name;
        this.store = store;
        this.price = price;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.match = false;
    }

    public Integer getCampaignId() {
        return CampaignId;
    }

    public void setCampaignId(int campaignId) {
        CampaignId = campaignId;
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

    public Boolean getMatch() {
        return match;
    }

    public void setMatch(Boolean match) {
        this.match = match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignDto that = (CampaignDto) o;
        return Objects.equals(name, that.name) && Objects.equals(store, that.store) && Objects.equals(price, that.price) && Objects.equals(dateBegin, that.dateBegin) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(match, that.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, store, price, dateBegin, dateEnd, match);
    }

    @Override
    public String toString() {
        return "CampaignDto{" +
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
