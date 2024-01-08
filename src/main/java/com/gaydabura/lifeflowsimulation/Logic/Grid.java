package com.gaydabura.lifeflowsimulation.Logic;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Grid {

  @Getter
  private final int numberOfRows;
  @Getter
  private final int numberOfColumns;
  private final int totalCells;
  @Getter
  private List<Cell> cells;

  public Grid(int numberOfrows, int numberOfColumns) {
    this.numberOfRows = numberOfrows;
    this.numberOfColumns = numberOfColumns;
    this.totalCells = numberOfColumns * numberOfrows;
    this.cells = new ArrayList<>();
  }

  public void setCells(List<Cell> cells) {
    this.cells = cells;
  }

  public void setGridFromString(String initialState) {
    String[] array = initialState.replace("\n", " ").split(" ");
    List<Cell> list = new ArrayList<>();
    int x = 0;
    int y = 0;

    for (String s : array) {
      Cell cell = new Cell(x, y, "X".equals(s));
      list.add(cell);
      x++;
      if (x == numberOfColumns) {
        x = 0;
        y++;
      }
    }

    setCells(list);
    System.out.println("-----------------------------------");
    System.out.println("Initial: ");
    printGrid();
  }

  public void printGrid() {
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        int index = i * numberOfColumns + j;
        System.out.print(index < cells.size() && cells.get(index).getLive() ? "X " : "  ");
      }
      System.out.println();
    }
  }

  public void simulateNextStep() {
    List<Cell> nextCells = new ArrayList<>();

    for (Cell cell : cells) {
      int liveNeighbors = countLiveNeighbors(cell);
      boolean nextState;

      if (cell.getLive()) {
        // Live cell with 2 or 3 live neighbors stays alive, else dies
        nextState = liveNeighbors == 2 || liveNeighbors == 3;
      } else {
        // Dead cell with exactly 3 live neighbors becomes alive
        nextState = liveNeighbors == 3;
      }

      nextCells.add(new Cell(cell.getXvalue(), cell.getYvalue(), nextState));
    }

    this.cells = nextCells;
  }



  private Cell simulateOneCell(Cell cell) {
    int numberOfLiveNeighbors = countLiveNeighbors(cell);
    Cell newCell = new Cell(cell.getXvalue(), cell.getYvalue(), false);

    if (cell.getLive()) {
      if (numberOfLiveNeighbors == 2 || numberOfLiveNeighbors == 3) {
        newCell.setLive(true);
      }
    } else if (numberOfLiveNeighbors == 3) {
      newCell.setLive(true);
    }

    return newCell;
  }

  private int countLiveNeighbors(Cell cell) {
    int count = 0;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (!(i == 0 && j == 0)) {
          Cell neighbor = findCell(cell.getXvalue() + i, cell.getYvalue() + j);
          if (neighbor != null && neighbor.getLive()) {
            count++;
          }
        }
      }
    }

    return count;
  }

  public Cell findCell(int x, int y) {
    x = (x + numberOfColumns) % numberOfColumns;
    y = (y + numberOfRows) % numberOfRows;

    for (Cell cell : cells) {
      if (cell.getXvalue() == x && cell.getYvalue() == y) {
        return cell;
      }
    }

    return null;
  }

  public String simulateNSteps(int numberOfSteps) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < numberOfSteps; i++) {
      simulateNextStep();
    }

    System.out.println("-----------------------------------");
    System.out.println("Result: ");
    printGrid();

    for (int i = 0; i < numberOfRows; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < numberOfColumns; j++) {
        int index = i * numberOfColumns + j;
        if (index < cells.size()) {
          row.append(cells.get(index).getLive() ? "X " : "O ");
        } else {
          System.out.println("Index out of bounds!");
        }
      }
      row.deleteCharAt(row.length() - 1);
      sb.append(row).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
}