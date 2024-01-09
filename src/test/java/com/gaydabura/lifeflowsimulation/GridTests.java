package com.gaydabura.lifeflowsimulation;

import com.gaydabura.lifeflowsimulation.Logic.Cell;
import com.gaydabura.lifeflowsimulation.Logic.Grid;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTests {

  private Grid grid;

  @BeforeEach
  void setUp() {
    grid = new Grid(3, 3);
    // Initialize grid with a known state
  }

  @Test
  void testSimulateNextStep() {
    grid.setCells(Arrays.asList(
        new Cell(0, 0, false), new Cell(1, 0, true), new Cell(2, 0, false),
        new Cell(0, 1, true), new Cell(1, 1, true), new Cell(2, 1, true),
        new Cell(0, 2, false), new Cell(1, 2, true), new Cell(2, 2, false)
    ));

    grid.simulateNextStep();

    assertFalse(grid.findCell(1, 1).getLive()); // Cell should die due to overpopulation
    assertFalse(grid.findCell(1, 0).getLive()); // Cell should stay alive
    assertFalse(grid.findCell(0, 2).getLive()); // Cell should become alive
  }


}
