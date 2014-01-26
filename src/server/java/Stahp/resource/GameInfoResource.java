package Stahp.resource;

import Stahp.entity.GameEntity;
import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class GameInfoResource {

    private final Logger logger = Logger.getLogger(GameResource.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private String currentPlayerKey;
    private String gameId;

    public GameInfoResource(String currentPlayerKey, String gameId) {
        this.currentPlayerKey = currentPlayerKey;
        this.gameId = gameId;
    }

    @GET
    public GameEntity getGameInfo() {
        // TODO: implement game state change
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(gameId);
        return gameEntity;
    }

    /**
     * Update a game state (allowed only for game owner)
     *
     * @return modified GameEntity
     */
    @POST
    public GameEntity changeGameState() {
        // TODO: implement game state change
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(gameId);
        return gameEntity;
    }

    /**
     * Game word list related requests
     *
     * @return
     */
    @Path("words")
    public GameWordResource gameWords() {
        return new GameWordResource(currentPlayerKey, gameId);
    }
}