package com.example.campaigntracker.service;

import com.example.campaigntracker.dto.CampaignDto;

import java.util.List;

public interface CampaignService {
    List<CampaignDto> findByShoppingItem(String item);
    List<CampaignDto> findAll();
    CampaignDto create(CampaignDto campaignDto);
    CampaignDto update(CampaignDto campaignDto);
    boolean delete(int campaignId);
}
