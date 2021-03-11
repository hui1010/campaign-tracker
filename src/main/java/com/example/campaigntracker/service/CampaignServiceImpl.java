package com.example.campaigntracker.service;

import com.example.campaigntracker.data.CampaignRepository;
import com.example.campaigntracker.dto.CampaignDto;
import com.example.campaigntracker.entity.Campaign;
import com.example.campaigntracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Configurable
public class CampaignServiceImpl implements CampaignService{

    CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    protected CampaignDto getCampaignDto(Campaign campaign) {
        CampaignDto campaignDto = new CampaignDto(campaign.getCampaignId(), campaign.getName(), campaign.getStore(), campaign.getAmount(), campaign.getPrice(), campaign.getDateBegin(), campaign.getDateEnd());
        campaignDto.setCampaignId(campaign.getCampaignId());
        campaignDto.setName(campaign.getName());
        campaignDto.setStore(campaign.getStore());
        campaignDto.setAmount(campaign.getAmount());
        campaignDto.setPrice(campaign.getPrice());
        campaignDto.setDateBegin(campaign.getDateBegin());
        campaignDto.setDateEnd(campaign.getDateEnd());
        return campaignDto;
    }

    protected List<CampaignDto> getCampaignDtos(List<Campaign> campaigns) {
        List<CampaignDto> results = new ArrayList<>();
        for (Campaign c : campaigns){
            CampaignDto campaignDto = getCampaignDto(c);
            results.add(campaignDto);
        } return results;
    }

    protected Campaign getCampaign(CampaignDto campaignDto){
        return new Campaign(campaignDto.getName(), campaignDto.getStore(), campaignDto.getAmount(), campaignDto.getPrice(), campaignDto.getDateBegin(), campaignDto.getDateEnd());
    }

    @Override
    public List<CampaignDto> findByShoppingItem(String item) {
        List<Campaign> foundItems = campaignRepository.findAllByNameContainingIgnoreCase(item);
        return getCampaignDtos(foundItems);
    }

    @Override
    public List<CampaignDto> findAll() {
        List<Campaign> foundItems = campaignRepository.findAll();
        return getCampaignDtos(foundItems);
    }

    @Override
    @Transactional
    public CampaignDto create(CampaignDto campaignDto) {
        List<Campaign> campaigns = campaignRepository.findAll();
        Campaign toCreate = new Campaign(campaignDto.getName(), campaignDto.getStore(), campaignDto.getPrice(), campaignDto.getDateBegin(), campaignDto.getDateEnd());
        List<Campaign> result = campaigns.stream().filter(campaign ->
            campaign.getName().equals(campaignDto.getName()) && campaign.getStore().equals(campaignDto.getStore()) &&
        campaign.getAmount() == campaignDto.getAmount() && campaign.getPrice() == campaignDto.getPrice() &&
        campaign.getDateBegin().isEqual(campaignDto.getDateBegin()) && campaign.getDateEnd().isEqual(campaignDto.getDateEnd())
        ).collect(Collectors.toList());
        if(!result.isEmpty())
            throw new RuntimeException("Campaign already exists");
        if(campaignDto.getDateEnd().isBefore(campaignDto.getDateBegin()))
            throw new IllegalArgumentException("End date should not be before begin date.");
        return getCampaignDto(campaignRepository.save(toCreate));
    }

    @Override
    @Transactional
    public CampaignDto update(CampaignDto campaignDto) throws RuntimeException, IllegalArgumentException {
//       if (!campaignRepository.existsById(campaignDto.getCampaignId()))
//            throw new RuntimeException("Campaign does not exist, please create first");
        Campaign campaign = campaignRepository.findById(campaignDto.getCampaignId()).orElseThrow(
                () -> new RuntimeException("Cannot find the campaign.")
        );


        if (!campaign.getName().equals(campaignDto.getName()))
            campaign.setName(campaignDto.getName());
        if (!campaign.getStore().equals(campaignDto.getStore()))
            campaign.setStore(campaignDto.getStore());
        if(campaign.getAmount() != campaignDto.getAmount())
            campaign.setAmount(campaignDto.getAmount());
        if (campaign.getPrice() != campaignDto.getPrice())
            campaign.setPrice(campaignDto.getPrice());

        if(campaignDto.getDateEnd().isBefore(campaignDto.getDateBegin()))
            throw new IllegalArgumentException("End date should not be before begin date.");

        if (!campaign.getDateBegin().isEqual(campaignDto.getDateBegin()))
            campaign.setDateBegin(campaignDto.getDateBegin());
        if (!campaign.getDateEnd().isEqual(campaignDto.getDateEnd()))
            campaign.setDateEnd(campaignDto.getDateEnd());

        List<Campaign> campaigns = campaignRepository.findAll();
        List<Campaign> result = campaigns.stream().filter(camp ->
                camp.getName().equals(campaignDto.getName()) && camp.getStore().equals(campaignDto.getStore()) &&
                        camp.getAmount() == campaignDto.getAmount() && camp.getPrice() == campaignDto.getPrice() &&
                        camp.getDateBegin().isEqual(campaignDto.getDateBegin()) && camp.getDateEnd().isEqual(campaignDto.getDateEnd())
        ).collect(Collectors.toList());

        if(result.size() > 1)
            throw new RuntimeException("Campaign already exists");

        return getCampaignDto(campaignRepository.save(campaign));
    }

    @Override
    @Transactional
    public boolean delete(int campaignId) throws ResourceNotFoundException{
        if (!campaignRepository.findById(campaignId).isPresent())
            throw new ResourceNotFoundException("Can not find the library user with id: " + campaignId);
        boolean deleted = false;
        if (campaignRepository.existsById(campaignId)){
            campaignRepository.delete(campaignRepository.findById(campaignId).get());
            deleted = true;
        }
        return deleted;
    }
}
