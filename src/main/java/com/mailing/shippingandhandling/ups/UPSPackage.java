package com.mailing.shippingandhandling.ups;

public class UPSPackage {
  private String name;
  private String address;
  private String shipFrom;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getShipFrom() {
    return shipFrom;
  }

  public void setShipFrom(String shipFrom) {
    this.shipFrom = shipFrom;
  }

}
