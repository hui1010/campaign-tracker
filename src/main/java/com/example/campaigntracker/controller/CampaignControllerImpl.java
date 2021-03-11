package com.example.campaigntracker.controller;

import com.example.campaigntracker.dto.CampaignDto;
import com.example.campaigntracker.service.CampaignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/campaigns")

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
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Object> find(
            @RequestParam(name="type", defaultValue = ALL) final String type,
            @RequestParam(name="value", defaultValue = "") final String value
    ){
            switch (type.toLowerCase().trim()) {
                case ALL:
                    return ResponseEntity.ok(campaignService.findAll());
                case ITEM:
                    return ResponseEntity.ok(campaignService.findByShoppingItem(value));
                default:
                    throw new IllegalArgumentException("Not a valid type: " + type);
            }

    }

    public ResponseEntity<Object> findByName (@RequestParam("name") String name) {
        return ResponseEntity.ok(campaignService.findByShoppingItem(name.toLowerCase().trim()));
    }

    @Override
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<CampaignDto> create(@Valid @RequestBody CampaignDto campaignDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignService.create(campaignDto));
    }

    @Override
    @PutMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<CampaignDto> update(@RequestBody CampaignDto campaignDto) {
        return ResponseEntity.ok(campaignService.update(campaignDto));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        boolean isRemoved = campaignService.delete(id);
        if(!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
