package Stahp.resource;

import Stahp.entity.MatchEntity;
import Stahp.game.GameController;
import Stahp.persistence.model.Match;
import Stahp.persistence.model.Player;
import Stahp.persistence.service.MatchService;
import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("matches")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MatchResource {

    private final Logger logger = Logger.getLogger(MatchResource.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private MatchService matchService;

    @Autowired
    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    private GameController gameController;

    @Autowired
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

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
    public List<MatchEntity> getGameList(
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
        return new ArrayList<MatchEntity>();
    }

    /**
     * Create a new game
     *
     * @return Response 201 with created game location
     */
    @POST
    public Response createMatch() {
        Player currentPlayer = getCurrentPlayer();
        Match match;

        try {
            match = gameController.createMatch(currentPlayer);
            matchService.save(match);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI createdUri = ub.
                path(match.getId()).
                build();
        return Response.created(createdUri).build();
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

    /**
     * Match details related requests
     *
     * @param matchId target match's id
     * @return
     */
    @Path("{id}")
    public MatchInfoResource matchInfo(
            @PathParam("id") String matchId) {
        Player currentPlayer = getCurrentPlayer();
        Match match;
        try {
            match = matchService.findById(matchId);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if(match == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        MatchInfoResource matchInfoResource = new MatchInfoResource(currentPlayer, match);
        beanFactory.autowireBean(matchInfoResource);
        return matchInfoResource;
    }
}



