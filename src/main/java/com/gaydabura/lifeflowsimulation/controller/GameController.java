package com.gaydabura.lifeflowsimulation.controller;

import com.gaydabura.lifeflowsimulation.Logic.Grid;
import com.gaydabura.lifeflowsimulation.model.GridState;
import com.gaydabura.lifeflowsimulation.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


@Controller
public class GameController {

  @Autowired
  private GameService gameService;

  @GetMapping("/game")
  public String game() {
    return "game"; // This should correspond to game.html in src/main/resources/templates
  }

  @PostMapping("/initialize")
  public ResponseEntity<?> initializeGrid(@RequestBody GridState gridState) {
    gameService.initializeGrid(gridState.getInitialState());
    return ResponseEntity.ok().build();
  }




  @GetMapping(path = "/nextStep", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<List<Boolean>>> nextStep() {
    List<List<Boolean>> nextState = gameService.getNextState();
    return ResponseEntity.ok(nextState);
  }

  @PostMapping("/reset")
  public ResponseEntity<?> resetGame() {
    gameService.resetGrid();
    return ResponseEntity.ok().build();
  }

}


