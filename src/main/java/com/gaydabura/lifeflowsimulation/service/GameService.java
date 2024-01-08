package com.gaydabura.lifeflowsimulation.service;

import com.gaydabura.lifeflowsimulation.Logic.Grid;
import com.gaydabura.lifeflowsimulation.Logic.Cell;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

  private Grid grid;



  public void startNewGame(int rows, int cols) {
    grid = new Grid(rows, cols);
    // Initialize the grid with dead cells
    List<Cell> cells = new ArrayList<>();
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        cells.add(new Cell(x, y, false));
      }
    }
    grid.setCells(cells);
  }

  public void initializeGrid(List<List<Boolean>> initialState) {
    int rows = initialState.size();
    int cols = rows > 0 ? initialState.get(0).size() : 0;
    grid = new Grid(rows, cols);

    List<Cell> cells = new ArrayList<>();
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        boolean isAlive = initialState.get(y).get(x);
        cells.add(new Cell(x, y, isAlive));
      }
    }
    grid.setCells(cells);
  }

  public void performStep() {
    grid.simulateNextStep();
  }

  public List<List<Boolean>> getNextState() {
    performStep(); // Update the grid for the next step

    List<List<Boolean>> nextState = new ArrayList<>();
    for (int i = 0; i < grid.getNumberOfRows(); i++) {
      List<Boolean> rowState = new ArrayList<>();
      for (int j = 0; j < grid.getNumberOfColumns(); j++) {
        Cell cell = grid.findCell(j, i);
        rowState.add(cell != null && cell.getLive());
      }
      nextState.add(rowState);
    }
    return nextState;
  }

  public void resetGrid() {
    int rows = grid.getNumberOfRows();
    int cols = grid.getNumberOfColumns();
    startNewGame(rows, cols); // Re-initialize the grid with all cells dead
  }




  public Grid getGrid() {
    return grid;
  }
}
