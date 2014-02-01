package Stahp.resource;

import Stahp.entity.ChallengeEntity;
import Stahp.entity.MatchEntity;
import Stahp.game.GameController;
import Stahp.game.RandomChallengeSelector;
import Stahp.persistence.model.Challenge;
import Stahp.persistence.model.Match;
import Stahp.persistence.model.Player;
import Stahp.persistence.service.ChallengeService;
import Stahp.persistence.service.MatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
            gameController.joinMatch(currentPlayer, match);
        }

        matchService.save(match);

        return new MatchEntity(match);
    }

    @Path("words")
    @GET
    public List<ChallengeEntity> getChallenges() {
        if(match == null || match.getChallengeList().isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        ArrayList<ChallengeEntity> challengeEntityList = new ArrayList<ChallengeEntity>();
        for(Challenge challenge: match.getChallengeList()) {
            challengeEntityList.add(new ChallengeEntity(challenge));
        }

        return challengeEntityList;
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