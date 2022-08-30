package com.globallogic.rest.controller;

import com.globallogic.rest.dto.FarmerDto;
import com.globallogic.rest.mapper.FarmerMapper;
import com.globallogic.rest.model.Farmer;
import com.globallogic.rest.server.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("farmers")
public class FarmerController {

    private final FarmerService farmerService;
    private final FarmerMapper farmerMapper;

    public FarmerController(FarmerService farmerService, FarmerMapper farmerMapper) {
        this.farmerService = farmerService;
        this.farmerMapper = farmerMapper;
    }
    @GetMapping("/hello")
    public ResponseEntity<String> testingChanges(){
        return ResponseEntity.ok().body("Hello!");
    }

    @GetMapping
    public ResponseEntity<List<FarmerDto>> getAllFarmers() {
        final List<Farmer> farmers = farmerService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(farmerMapper.toDto(farmers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerDto> getFarmerById(@PathVariable Long id) {
        final Farmer farmer = farmerService.getFarmerById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(farmerMapper.toDto(farmer));
    }

    @PostMapping
    public ResponseEntity<FarmerDto> createFarmer(@RequestBody @Valid final FarmerDto farmerDto) {
        final Farmer farmer = farmerMapper.toEntity(farmerDto);
        final Farmer createdFarmer = farmerService.createFarmer(farmer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(farmerMapper.toDto(createdFarmer));
    }


    @PutMapping
    public ResponseEntity<?> updateFarmer(@RequestBody @Valid final FarmerDto farmerDto) {
        final Farmer farmer = farmerMapper.toEntity(farmerDto);
        final Farmer createdFarmer = farmerService.updateFarmer(farmer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(farmerMapper.toDto(createdFarmer));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchFarmer(@PathVariable final Long id, @RequestBody final Map<String, Object> fieldsToUpdate) {
        final Farmer farmer = farmerService.getFarmerById(id);
        fieldsToUpdate.forEach((fieldName, value) -> {
            final Field field = ReflectionUtils.findField(Farmer.class, fieldName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, farmer, value);
        });
        final Farmer createdFarmer = farmerService.updateFarmer(farmer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(farmerMapper.toDto(createdFarmer));
    }


}
