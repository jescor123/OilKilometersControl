package com.oil.oillevel.service;

import com.oil.oillevel.exceptions.OilLevelNotFoundException;
import com.oil.oillevel.models.OilLevel;
import com.oil.oillevel.repository.OilLevelRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class OilLevelService {

  @Autowired
  private OilLevelRepository oilLevelRepository;

  public List<OilLevel> listAllKilometers() {

    return oilLevelRepository.findAll();

  }

  public OilLevel updateKilometersByCarId(OilLevel oilLevel, int id) {

    return oilLevelRepository.findById(id).map(o -> {
      o.setCarName(oilLevel.getCarName());
      o.setKilometers(oilLevel.getKilometers());
      return oilLevelRepository.save(o);
    })
        .orElseGet(() -> {
               oilLevel.setId(id);
               return  oilLevelRepository.save(oilLevel);
            }
        );

  }

  public OilLevel getOilLevelEntity(int id){

     return oilLevelRepository.findById(id)
         .orElseThrow(() -> new OilLevelNotFoundException(id));

  }

  public OilLevel addNewCar(OilLevel oilLevel){

    return oilLevelRepository.save(oilLevel);

  }

  public void deleteOilLevelEntity(int id){

    oilLevelRepository.deleteById(id);

  }

}
