package Stahp.resource;

import Stahp.entity.GameEntity;
import Stahp.persistence.dto.Game;
import Stahp.persistence.dto.Player;
import Stahp.persistence.service.GameService;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class GameInfoResource {

    private final Logger logger = Logger.getLogger(GameInfoResource.class.getName());

    private GameService gameService;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    private Player currentPlayer;
    private Game game;

    public GameInfoResource(Player currentPlayer, Game game) {
        this.currentPlayer = currentPlayer;
        this.game = game;
    }

    @GET
    public GameEntity getGameInfo() {
        return new GameEntity(game);
    }

    /**
     * Update a game state (allowed only for game owner)
     *
     * @return modified GameEntity
     */
    @POST
    public GameEntity changeGameState() {
        return new GameEntity(game);
    }

    /**
     * Game word list related requests
     *
     * @return
     */
    @Path("words")
    public GameWordResource gameWords() {
        GameWordResource gameWordResource = new GameWordResource(currentPlayer, game);
        gameWordResource.setGameService(gameService);
        return gameWordResource;
    }
}