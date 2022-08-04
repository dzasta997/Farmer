package com.globallogic.rest.server;

import com.globallogic.rest.exception.ApplicationException;
import com.globallogic.rest.exception.UpdatingEntityWithoutIdException;
import com.globallogic.rest.model.Farmer;
import com.globallogic.rest.reposiotry.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer createFarmer(final Farmer farmer) {
        if(getByName(farmer) !=null){
            throw new ApplicationException("Farmer already exists");
        }
        return farmerRepository.save(farmer);
    }

    private Farmer getByName(Farmer farmer) {
        return farmerRepository.findByName(farmer.getName());
    }

    public Farmer updateFarmer(final Farmer farmer) {
        if (farmer.getId() == null) {
            throw new UpdatingEntityWithoutIdException(Farmer.class);
        }
        return farmerRepository.save(farmer);
    }


    public Farmer getFarmerById(final Long farmerId) {
        return farmerRepository.findById(farmerId)
                .orElseThrow();
    }

    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

}
