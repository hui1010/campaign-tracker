package com.example.campaigntracker.data;

import com.example.campaigntracker.entity.Campaign;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CampaignRepository extends CrudRepository<Campaign, Integer> {
    List<Campaign> findAll();
    List<Campaign> findAllByNameContainingIgnoreCase(String name);
}
