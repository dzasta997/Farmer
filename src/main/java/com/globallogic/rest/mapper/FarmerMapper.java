package com.globallogic.rest.mapper;

import com.globallogic.rest.dto.FarmerDto;
import com.globallogic.rest.model.Farmer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FarmerMapper extends ApplicationMapper<Farmer, FarmerDto> {

    @Override
    public Farmer toEntity(final FarmerDto farmerDto) {
        return new Farmer()
                .setId(farmerDto.getId())
                .setName(farmerDto.getName())
                .setStatus(farmerDto.getStatus())
                .setCity(farmerDto.getCity());
    }

    @Override
    public FarmerDto toDto(final Farmer farmer) {
        final String status = Optional.of(farmer)
                .map(Farmer::getStatus)
                .map(String::toUpperCase)
                .orElse("UNKNOWN");
        return new FarmerDto()
                .setId(farmer.getId())
                .setName(farmer.getName())
                .setStatus(status)
                .setCity(farmer.getCity());
    }
}
