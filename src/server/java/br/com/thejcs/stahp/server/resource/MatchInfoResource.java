package br.com.thejcs.stahp.server.resource;

import br.com.thejcs.stahp.server.entity.MatchEntity;
import br.com.thejcs.stahp.server.game.GameController;
import br.com.thejcs.stahp.server.game.RandomChallengeSelector;
import br.com.thejcs.stahp.server.persistence.model.Challenge;
import br.com.thejcs.stahp.server.persistence.model.Match;
import br.com.thejcs.stahp.server.persistence.model.Player;
import br.com.thejcs.stahp.server.persistence.service.ChallengeService;
import br.com.thejcs.stahp.server.persistence.service.MatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class MatchInfoResource {

    private final Logger logger = Logger.getLogger(MatchInfoResource.class.getName());

    @Autowired
    private MatchService matchService;

    @Autowired
    private GameController gameController;

    @Autowired
    private ChallengeService challengeService;

    private Player currentPlayer;
    private Match match;

    public MatchInfoResource(Player currentPlayer, Match match) {
        this.currentPlayer = currentPlayer;
        this.match = match;
    }

    /**
     * Get the match information
     *
     * @return the MatchEntity
     */
    @GET
    public MatchEntity getMatchInfo() {
        try {
            return new MatchEntity(match);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

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

        if(match.getCreator().equals(currentPlayer)) {
            RandomChallengeSelector challengeSelector = new RandomChallengeSelector();

            // TODO: Autowire this class
            challengeSelector.setChallengeService(challengeService);

            gameController.startMatch(match, challengeSelector);

            for(Challenge challenge: match.getChallengeList()) {
                logger.info("Challenge: " + challenge.getTopic().getDescription() + " starting with " + challenge.getInitial());
            }
        }
        else {
            if(gameController.isPlayerInMatch(currentPlayer, match)) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }

            gameController.joinMatch(currentPlayer, match);
        }

        matchService.save(match);

        return new MatchEntity(match);
    }

    @Path("words")
    public MatchWordResource matchWords(
            @PathParam("matchId") String matchId) {
        if(match == null || match.getChallengeList().isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        MatchWordResource matchWordResource;
        matchWordResource = new MatchWordResource(currentPlayer, match);

        // TODO: Autowire this class
        matchWordResource.setGameController(gameController);
        matchWordResource.setMatchService(matchService);

        return matchWordResource;
    }

    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }
}