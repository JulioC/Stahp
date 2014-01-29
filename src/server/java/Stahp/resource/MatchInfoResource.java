package Stahp.resource;

import Stahp.entity.MatchEntity;
import Stahp.game.ChallengeSelector;
import Stahp.game.DummyChallengeSelector;
import Stahp.game.GameController;
import Stahp.persistence.model.Match;
import Stahp.persistence.model.Player;
import Stahp.persistence.service.MatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MatchInfoResource {

    private final Logger logger = Logger.getLogger(MatchInfoResource.class.getName());

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

    private Player currentPlayer;
    private Match match;

    public MatchInfoResource(Player currentPlayer, Match match) {
        this.currentPlayer = currentPlayer;
        this.match = match;
    }

    @GET
    public MatchEntity getMatchInfo() {
        return new MatchEntity(match);
    }

    /**
     * Update a match state (allowed only for match owner)
     *
     * @return modified MatchEntity
     */
    @POST
    public MatchEntity updateMatch() {
        if(match.getStatus() != Match.Status.CREATED){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if(match.getCreator() == currentPlayer) {
            ChallengeSelector challengeSelector = new DummyChallengeSelector();
            gameController.startMatch(match, challengeSelector);
        }
        else {
            gameController.joinMatch(currentPlayer, match);
        }

        matchService.save(match);

        return new MatchEntity(match);
    }

    // TODO: add word related methods

}