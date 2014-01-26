package Stahp.resource;

import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class GameWordResource {
    private final Logger logger = Logger.getLogger(GameResource.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private String currentPlayerKey;
    private String gameId;

    public GameWordResource(String currentPlayerKey, String gameId) {
        this.currentPlayerKey = currentPlayerKey;
        this.gameId = gameId;
    }

    // TODO: implement word list related methods
}
