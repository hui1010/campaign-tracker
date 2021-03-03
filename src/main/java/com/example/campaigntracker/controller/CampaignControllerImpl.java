package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.CampaignDto;
import com.example.campaigntracker.service.CampaignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/campaigns")
public class CampaignControllerImpl implements CampaignController{

    public static final String ALL = "all";
    public static final String ITEM = "item";

    CampaignServiceImpl campaignService;

    @Autowired
    public CampaignControllerImpl(CampaignServiceImpl campaignService) {
        this.campaignService = campaignService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(name = "type", defaultValue = ALL) String type,
            @RequestParam(name = "value", defaultValue = ALL) String value) {

        switch(type.toLowerCase().trim()){
            case ALL:
                return ResponseEntity.ok(campaignService.findAll());
            case ITEM:
                return ResponseEntity.ok(campaignService.findByShoppingItem(value));
            default:
                throw new RuntimeException("Not a valid type: " + type);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<CampaignDto> save(@Valid @RequestBody CampaignDto campaignDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignService.create(campaignDto));
    }

    @Override
    @PutMapping
    public ResponseEntity<CampaignDto> update(@RequestBody CampaignDto campaignDto) {
        return ResponseEntity.ok(campaignService.update(campaignDto));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Integer> delete(Integer id) {
        boolean isRemoved = campaignService.delete(id);
        if(!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
