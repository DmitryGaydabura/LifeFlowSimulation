package com.gaydabura.lifeflowsimulation.Logic;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class GameOfLife {

  public void game(String fileNameInput, String fileNameOutput) {
    try {
      // Read input from the file
      String input = readFromFile(fileNameInput);

      // Main logic here
      String output = start(input);

      // Write output to the file
      writeToFile(fileNameOutput, output);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String readFromFile(String fileName) throws IOException {
    try (InputStream inputResource = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputResource))) {
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }

  private void writeToFile(String fileName, String content) throws IOException {
    Path outputPath = Paths.get("src", "test", "resources", fileName);
    try (OutputStream outputResource = Files.newOutputStream(outputPath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputResource))) {
      writer.write(content);
    }
  }

  private String start(String input) {
    String[] inputLines = input.split("\n");
    int numberOfRows = Integer.parseInt(inputLines[0].split(",")[0]);
    int numberOfColumns = Integer.parseInt(inputLines[0].split(",")[1]);
    int numberOfSteps = Integer.parseInt(inputLines[0].split(",")[2]);
    String field = input.substring(input.indexOf("\n") + 1);

    Grid grid = new Grid(numberOfRows, numberOfColumns);
    grid.setGridFromString(field);

    return grid.simulateNSteps(numberOfSteps);
  }
}
