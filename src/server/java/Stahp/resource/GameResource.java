package Stahp.resource;

import Stahp.entity.GameEntity;
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

    @Context
    private UriInfo uriInfo;

    @NotNull @QueryParam("key")
    private String currentPlayerKey;

    /**
     * Get the game list for a player or for the logged player
     *
     * @param playerId optional target player
     * @return list of GameResources
     */
    @GET
    public List<GameEntity> getGameList(
            @QueryParam("player") String playerId) {
        if(playerId == null) {
            playerId = currentPlayerKey;
        }

        // TODO: implement game listing by player
        return new ArrayList<GameEntity>();
    }

    /**
     * Create a new game
     *
     * @return Response 201 with created game location
     */
    @POST
    public Response createGame() {
        // TODO: implement game creation
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI createdUri = ub.
                path("id").
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
        return new GameInfoResource(currentPlayerKey, gameId);
    }
}



