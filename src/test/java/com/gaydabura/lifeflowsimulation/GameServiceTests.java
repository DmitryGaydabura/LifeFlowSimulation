package com.gaydabura.lifeflowsimulation;

import com.gaydabura.lifeflowsimulation.Logic.Grid;
import com.gaydabura.lifeflowsimulation.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTests {

  private GameService gameService;

  @BeforeEach
  void setUp() {
    gameService = new GameService();
  }

  @Test
  void testInitializeGrid() {
    List<List<Boolean>> initialState = Arrays.asList(
        Arrays.asList(false, true, false),
        Arrays.asList(true, true, true),
        Arrays.asList(false, false, false)
    );

    gameService.initializeGrid(initialState);
    Grid grid = gameService.getGrid();

    assertEquals(3, grid.getNumberOfRows());
    assertEquals(3, grid.getNumberOfColumns());
    assertTrue(grid.findCell(1, 0).getLive());
    assertFalse(grid.findCell(0, 0).getLive());
  }

  @Test
  void testPerformStep() {
    List<List<Boolean>> initialState = Arrays.asList(
        Arrays.asList(false, true, false),
        Arrays.asList(true, true, true),
        Arrays.asList(false, true, false)
    );

    gameService.initializeGrid(initialState);
    gameService.performStep();
    Grid grid = gameService.getGrid();

    assertFalse(grid.findCell(1, 1).getLive()); // Cell should die due to overpopulation
    assertFalse(grid.findCell(1, 0).getLive()); // Cell should stay alive
  }


  @Test
  void testResetGrid() {
    List<List<Boolean>> initialState = Arrays.asList(
        Arrays.asList(true, true, true),
        Arrays.asList(true, true, true),
        Arrays.asList(true, true, true)
    );

    gameService.initializeGrid(initialState);
    gameService.resetGrid();
    Grid grid = gameService.getGrid();

    for (int i = 0; i < grid.getNumberOfRows(); i++) {
      for (int j = 0; j < grid.getNumberOfColumns(); j++) {
        assertFalse(grid.findCell(j, i).getLive());
      }
    }
  }
}
