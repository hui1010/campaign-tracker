package com.example.campaigntracker.service;

import com.example.campaigntracker.data.CampaignRepository;
import com.example.campaigntracker.dto.CampaignDto;
import com.example.campaigntracker.entity.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Configurable
public class CampaignServiceImpl implements CampaignService{

    CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    protected CampaignDto getCampaignDto(Campaign campaign) {
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setCampaignId(campaign.getCampaignId());
        campaignDto.setName(campaign.getName());
        campaignDto.setStore(campaign.getStore());
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

        return new Campaign(campaignDto.getName(), campaignDto.getStore(), campaignDto.getPrice(), campaignDto.getDateBegin(), campaignDto.getDateEnd());
    }

    @Override
    public List<CampaignDto> findByShoppingItem(String item) {
        return null;
    }

    @Override
    public List<CampaignDto> findAll() {
        List<Campaign> foundItems = campaignRepository.findAll();
        return getCampaignDtos(foundItems);
    }

    @Override
    @Transactional
    public CampaignDto create(CampaignDto campaignDto) {
        if (campaignRepository.existsById(campaignDto.getCampaignId()))
            throw new RuntimeException("Campaign already exists, please update");
        Campaign toCreate = new Campaign(campaignDto.getName(), campaignDto.getStore(), campaignDto.getPrice(), campaignDto.getDateBegin(), campaignDto.getDateEnd());
        return getCampaignDto(campaignRepository.save(toCreate));
    }

    @Override
    @Transactional
    public CampaignDto update(CampaignDto campaignDto) throws RuntimeException {
        if (!campaignRepository.existsById(campaignDto.getCampaignId()))
            throw new RuntimeException("Library user does not exist, please create first");
        Campaign campaign = campaignRepository.findById(campaignDto.getCampaignId()).orElseThrow(
                () -> new RuntimeException("Cannot find the user.")
        );
        if (!campaign.getName().equals(campaignDto.getName()))
            campaign.setName(campaignDto.getName());
        if (!campaign.getStore().equals(campaignDto.getStore()))
            campaign.setStore(campaign.getStore());
        if (campaign.getPrice() != campaignDto.getPrice())
            campaign.setPrice(campaignDto.getPrice());
        if (!campaign.getDateBegin().isEqual(campaignDto.getDateBegin()))
            campaign.setDateBegin(campaignDto.getDateBegin());
        if (!campaign.getDateEnd().isEqual(campaignDto.getDateEnd()))
            campaign.setDateEnd(campaignDto.getDateEnd());
        if (campaign.isMeet() != campaignDto.getMeet())
            campaign.setMeet(campaignDto.getMeet());
        return getCampaignDto(campaignRepository.save(campaign));
    }

    @Override
    @Transactional
    public boolean delete(int campaignId) throws RuntimeException{
        if (!campaignRepository.findById(campaignId).isPresent())
            throw new RuntimeException("Can not find the library user with id: " + campaignId);
        boolean deleted = false;
        if (campaignRepository.existsById(campaignId)){
            campaignRepository.delete(campaignRepository.findById(campaignId).get());
            deleted = true;
        }
        return deleted;
    }
}
