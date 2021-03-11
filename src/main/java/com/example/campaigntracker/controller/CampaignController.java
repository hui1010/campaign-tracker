package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.CampaignDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CampaignController {
    ResponseEntity<Object> find(final String type, final String value);
    ResponseEntity<CampaignDto> create(CampaignDto campaignDto);
    ResponseEntity<CampaignDto> update(CampaignDto campaignDto);
    ResponseEntity<Integer> delete (Integer id);
}
