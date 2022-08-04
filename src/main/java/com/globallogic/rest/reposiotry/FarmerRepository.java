package com.globallogic.rest.reposiotry;

import com.globallogic.rest.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Farmer findByName(String name);

}
