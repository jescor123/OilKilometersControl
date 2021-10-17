package com.oil.oillevel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oilcontrol")
public class OilLevel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "car_name")
  private String carName;

  @Column(name = "kilometers")
  private long kilometers;

  public OilLevel() {

  }

  public OilLevel(int id, String carName, long kilometers) {
    this.id = id;
    this.carName = carName;
    this.kilometers = kilometers;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCarName() {
    return carName;
  }

  public void setCarName(String carName) {
    this.carName = carName;
  }

  public long getKilometers() {
    return kilometers;
  }

  public void setKilometers(long kilometers) {
    this.kilometers = kilometers;
  }

  @Override
  public String toString() {
    return "OilLevel{" +
        "id=" + id +
        ", carName='" + carName + '\'' +
        ", kilometers=" + kilometers +
        '}';
  }

}
