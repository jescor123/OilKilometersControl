package com.oil.oillevel.controllers;

import com.oil.oillevel.models.OilLevel;
import com.oil.oillevel.service.OilLevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kilometers")
public class OilLevelController {

  @Autowired
  OilLevelService oilLevelService;

  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<List<OilLevel>> getListAllKilometers() {

    List<OilLevel> listOilLevel = oilLevelService.listAllKilometers();
    return new ResponseEntity<List<OilLevel>>(listOilLevel, HttpStatus.OK);

  }

  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<OilLevel> updateKilometers(@RequestBody OilLevel oilLevel, @PathVariable int id) {

    if(oilLevel != null){
       oilLevelService.updateKilometersByCarId(oilLevel, id);
    }
    return new ResponseEntity<OilLevel>(oilLevel, HttpStatus.OK);

  }

  @GetMapping("/one/{id}")
  @ResponseBody
  public ResponseEntity<OilLevel> getOne(@PathVariable int id) {

    OilLevel oilLevel = oilLevelService.getOilLevelEntity(id);
    return new ResponseEntity<OilLevel>(oilLevel, HttpStatus.OK);

  }

  @PostMapping(value = "/new",consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OilLevel> newOilLevel(@RequestBody OilLevel oilLevel) {

    oilLevelService.addNewCar(oilLevel);
    return new ResponseEntity<OilLevel>(oilLevel, HttpStatus.CREATED);

  }

  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public ResponseEntity<Void> deleteOilLevel(@PathVariable int id) {

    oilLevelService.deleteOilLevelEntity(id);
    return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

  }

}
