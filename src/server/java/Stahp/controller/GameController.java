package Stahp.controller;

import Stahp.persistence.service.PlayerService;
import Stahp.resource.GameResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("games")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GameController {

    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @NotNull @QueryParam("key")
    private String currentPlayerKey;

    /**
     * Get the game list for a player or for the logged player
     *
     * @param playerId optional target player
     * @return list of GameResources
     */
    @GET
    public List<GameResource> getGameList(
            @QueryParam("player") String playerId) {
        if(playerId == null) {
            playerId = currentPlayerKey;
        }

        // TODO: implement game listing by player
        return new ArrayList<GameResource>();
    }

    /**
     * Create a new game
     *
     * @return GameResource for the created game
     */
    @POST
    public GameResource createGame() {
        // TODO: implement game creation
        return new GameResource();
    }

    /**
     * Game details related requests
     *
     * @param gameId target game's id
     * @return
     */
    @Path("{id}")
    public GameInfoController gameInfo(
            @PathParam("id") String gameId) {
        return new GameInfoController(currentPlayerKey, gameId);
    }
}



