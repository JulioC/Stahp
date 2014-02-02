package br.com.thejcs.stahp.server.resource;

import br.com.thejcs.stahp.server.entity.MatchEntity;
import br.com.thejcs.stahp.server.game.GameController;
import br.com.thejcs.stahp.server.persistence.model.Match;
import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;
import br.com.thejcs.stahp.server.persistence.service.ChallengeService;
import br.com.thejcs.stahp.server.persistence.service.MatchService;
import br.com.thejcs.stahp.server.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private GameController gameController;

    @Autowired
    private ChallengeService challengeService;

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

        ArrayList<MatchEntity> list = new ArrayList<MatchEntity>();
        for(MatchPlayer matchPlayer: player.getMatches()) {
            list.add(new MatchEntity(matchPlayer.getMatch()));
        }

        return list;
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
        return Response.created(createdUri).entity(new MatchEntity(match)).build();
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
    @Path("{matchId}")
    public MatchInfoResource matchInfo(
            @PathParam("matchId") String matchId) {
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

        MatchInfoResource matchInfoResource;
        matchInfoResource = new MatchInfoResource(currentPlayer, match);

        // TODO: Autowire this class
        matchInfoResource.setChallengeService(challengeService);
        matchInfoResource.setGameController(gameController);
        matchInfoResource.setMatchService(matchService);

        return matchInfoResource;
    }
}



