package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.CampaignDto;
import org.springframework.http.ResponseEntity;

public interface CampaignController {
    ResponseEntity<Object> find(final String type, final String value);
    ResponseEntity<CampaignDto> save(CampaignDto campaignDto);
    ResponseEntity<CampaignDto> update(CampaignDto campaignDto);
    ResponseEntity<Integer> delete (Integer id);
}
