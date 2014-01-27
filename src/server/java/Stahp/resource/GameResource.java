package Stahp.resource;

import Stahp.entity.GameEntity;
import Stahp.persistence.dto.Game;
import Stahp.persistence.dto.Player;
import Stahp.persistence.service.GameService;
import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("games")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GameResource {

    private final Logger logger = Logger.getLogger(GameResource.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Context
    private UriInfo uriInfo;

    @NotNull @QueryParam("key")
    private String currentPlayerKey;

    /**
     * Get the game list for a currentPlayer or for the logged currentPlayer
     *
     * @param playerId optional target currentPlayer
     * @return list of GameResources
     */
    @GET
    public List<GameEntity> getGameList(
            @QueryParam("player") String playerId) {
        Player player;
        if(playerId != null) {
            try {
                player = playerService.findById(playerId);
            }
            catch (Exception e) {
                logger.error(e);
                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            }

            if(player == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        else {
            player = getCurrentPlayer();
        }

        // TODO: implement game listing by currentPlayer
        return new ArrayList<GameEntity>();
    }

    /**
     * Create a new game
     *
     * @return Response 201 with created game location
     */
    @POST
    public Response createGame() {
        Player currentPlayer = getCurrentPlayer();
        Game game;

        try {
            game = new Game(currentPlayer);
            gameService.save(game);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI createdUri = ub.
                path(game.getId()).
                build();
        return Response.created(createdUri).build();
    }

    /**
     * Game details related requests
     *
     * @param gameId target game's id
     * @return
     */
    @Path("{id}")
    public GameInfoResource gameInfo(
            @PathParam("id") String gameId) {
        Player currentPlayer = getCurrentPlayer();
        Game game;
        try {
            game = gameService.findById(gameId);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if(game == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        GameInfoResource gameInfoResource = new GameInfoResource(currentPlayer, game);
        gameInfoResource.setGameService(gameService);
        return gameInfoResource;
    }

    private Player getCurrentPlayer() {
        Player currentPlayer;

        try {
            currentPlayer = playerService.findById(currentPlayerKey);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if(currentPlayer == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        return currentPlayer;
    }
}



