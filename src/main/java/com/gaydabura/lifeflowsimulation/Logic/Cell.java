package com.gaydabura.lifeflowsimulation.Logic;

import lombok.Getter;

public class Cell {

  @Getter
  int xvalue;
  @Getter
  int yvalue;
  Boolean isLive;

  public Cell(int x, int y, boolean isLive) {
    this.xvalue = x;
    this.yvalue = y;
    this.isLive = isLive;
  }

  public Boolean getLive() {
    return isLive;
  }


  public void setLive(Boolean live) {
    isLive = live;
  }


}
