package Stahp.controller;

import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class GameWordController {
    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private String currentPlayerKey;
    private String gameId;

    public GameWordController(String currentPlayerKey, String gameId) {
        this.currentPlayerKey = currentPlayerKey;
        this.gameId = gameId;
    }

    // TODO: implement word list related methods
}
