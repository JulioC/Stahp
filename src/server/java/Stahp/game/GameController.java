package Stahp.game;

import Stahp.persistence.dto.Match;
import Stahp.persistence.dto.Player;

public class GameController {

    public Match createMatch(Player player) {
        Match match = new Match(player);
        match.addPlayer(player);
        return match;
    }

    public void startMatch(Match match) {
        // TODO: generate words

        match.setStatus(MatchStatus.STARTED);
    }

    public void joinMatch(Player player, Match match) {
        match.addPlayer(player);
    }



}
