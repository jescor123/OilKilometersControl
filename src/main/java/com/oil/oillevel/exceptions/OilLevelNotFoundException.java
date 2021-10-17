package com.oil.oillevel.exceptions;

public class OilLevelNotFoundException extends RuntimeException {

  public OilLevelNotFoundException(int id){
     super("Could not find OilLevel entity with id : " + id);
  }

}
