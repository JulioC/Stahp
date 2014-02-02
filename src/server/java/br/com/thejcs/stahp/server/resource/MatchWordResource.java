package br.com.thejcs.stahp.server.resource;

import br.com.thejcs.stahp.server.entity.ChallengeEntity;
import br.com.thejcs.stahp.server.entity.ResponseEntity;
import br.com.thejcs.stahp.server.game.GameController;
import br.com.thejcs.stahp.server.persistence.model.Challenge;
import br.com.thejcs.stahp.server.persistence.model.Match;
import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;
import br.com.thejcs.stahp.server.persistence.service.MatchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchWordResource {

    private final Logger logger = Logger.getLogger(MatchInfoResource.class.getName());

    @Autowired
    private MatchService matchService;

    @Autowired
    private GameController gameController;

    private Player currentPlayer;
    private Match match;

    public MatchWordResource(Player currentPlayer, Match match) {
        this.currentPlayer = currentPlayer;
        this.match = match;
    }

    @GET
    public List<ChallengeEntity> getChallenges() {
        ArrayList<ChallengeEntity> challengeEntityList = new ArrayList<ChallengeEntity>();
        for(Challenge challenge: match.getChallengeList()) {
            challengeEntityList.add(new ChallengeEntity(challenge));
        }

        return challengeEntityList;
    }

    @POST
    public ResponseEntity sendWordList(
            @NotNull @FormParam("words") String words) {
        if(!gameController.isPlayerInMatch(currentPlayer, match)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        if(gameController.playerResponded(currentPlayer, match)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        // TODO: get the wordlist directly from the request
        List<String> wordList = Arrays.asList(words.split(","));

        gameController.setResponse(currentPlayer, match, wordList);
        matchService.save(match);

        MatchPlayer matchPlayer = gameController.getMatchPlayer(currentPlayer, match);
        return new ResponseEntity(matchPlayer);
    }

    @Path("{playerId}")
    @GET
    public ResponseEntity getPlayerResponse(
            @NotNull @PathParam("playerId") String playerId) {
        MatchPlayer matchPlayer = gameController.getMatchPlayer(playerId, match);
        if(matchPlayer == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return new ResponseEntity(matchPlayer);
    }

    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
