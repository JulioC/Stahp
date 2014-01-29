package Stahp.game;

import Stahp.persistence.model.Challenge;
import Stahp.persistence.model.Match;
import Stahp.persistence.model.Player;

import java.util.List;

public class GameController {

    private static Integer matchTimeLimit = 60;

    private static Integer challengeListSize = 100;

    public Match createMatch(Player player) {
        Match match = new Match(player, matchTimeLimit);
        match.addPlayer(player);
        return match;
    }

    public void startMatch(Match match, ChallengeSelector challengeSelector) {
        List<Challenge> challengeList = challengeSelector.generateList(challengeListSize);
        match.setChallengeList(challengeList);
        match.setStatus(Match.Status.STARTED);
    }

    public void joinMatch(Player player, Match match) {
        match.addPlayer(player);
    }


}
