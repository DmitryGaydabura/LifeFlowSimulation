package com.gaydabura.lifeflowsimulation.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
public class GridState {
  @Setter
  private List<List<Boolean>> initialState;
  // Getters and setters
}
