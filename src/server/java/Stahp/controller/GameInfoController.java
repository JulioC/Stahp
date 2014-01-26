package Stahp.controller;

import Stahp.persistence.service.PlayerService;
import Stahp.resource.GameResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class GameInfoController {

    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private String currentPlayerKey;
    private String gameId;

    public GameInfoController(String currentPlayerKey, String gameId) {
        this.currentPlayerKey = currentPlayerKey;
        this.gameId = gameId;
    }

    @GET
    public GameResource getGameInfo() {
        // TODO: implement game state change
        GameResource gameResource = new GameResource();
        gameResource.setId(gameId);
        return gameResource;
    }

    /**
     * Update a game state (allowed only for game owner)
     *
     * @return modified GameResource
     */
    @POST
    public GameResource changeGameState() {
        // TODO: implement game state change
        GameResource gameResource = new GameResource();
        gameResource.setId(gameId);
        return gameResource;
    }

    /**
     * Game word list related requests
     *
     * @return
     */
    @Path("words")
    public GameWordController gameWords() {
        return new GameWordController(currentPlayerKey, gameId);
    }
}