package com.globallogic.rest.server;

import com.globallogic.rest.exception.ApplicationException;
import com.globallogic.rest.exception.UpdatingEntityWithoutIdException;
import com.globallogic.rest.model.Farmer;
import com.globallogic.rest.reposiotry.FarmerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.Mockito.*;
@SpringBootTest
class FarmerServiceTest {

    @Mock
    FarmerRepository farmerRepository;
    @InjectMocks
    FarmerService farmerService;

    @Test
    void shouldSaveFarmerWhenNotExisting(){
        Farmer farmer = new Farmer()
                .setName("name")
                .setCity("city")
                .setStatus("farming");

        Farmer mockedFarmer = getMockedFarmer();
        when(farmerRepository.findByName("name")).thenReturn(null);
        when(farmerRepository.save(any())).thenReturn(mockedFarmer);
        Farmer createdFarmer = farmerService.createFarmer(farmer);
        assertNotNull(createdFarmer);
        assertNotNull(createdFarmer.getId());

    }

    private Farmer getMockedFarmer() {
        Farmer mockedFarmer = new Farmer()
                .setName("name")
                .setCity("city")
                .setStatus("farming")
                .setId(1l);
        return mockedFarmer;
    }

    @Test
    void shouldNotSaveFarmerWhenExisting(){
        Farmer mockedFarmer = getMockedFarmer();
        when(farmerRepository.findByName(any())).thenReturn(mockedFarmer);
        assertThrows(ApplicationException.class, ()->farmerService.createFarmer(mockedFarmer));
    }

    @Test
    void shouldCallRepositoryOnlyOnce(){
        farmerService.findAll();
        verify(farmerRepository, times(1)).findAll();
    }
    @Test
    void shouldThrowExceptionWhenFarmerNotFound(){
        Farmer farmer = new Farmer()
                .setName("name")
                .setCity("city");
        assertThrows(UpdatingEntityWithoutIdException.class, ()->farmerService.updateFarmer(farmer));
    }

    @Test
    void shouldUpdateFarmerWhenValidData(){
        Farmer mockedFarmer = getMockedFarmer();
        when(farmerRepository.save(mockedFarmer)).thenReturn(mockedFarmer);
        Farmer updatedFarmer = farmerService.updateFarmer(mockedFarmer);
        assertNotNull(updatedFarmer);
    }
}