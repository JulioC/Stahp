package br.com.thejcs.stahp.server.game;

import br.com.thejcs.stahp.server.persistence.model.Challenge;
import br.com.thejcs.stahp.server.persistence.model.Match;
import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    public boolean isPlayerInMatch(Player player, Match match) {
        if(getMatchPlayer(player, match) == null) {
            return false;
        }

        return true;
    }

    public boolean playerResponded(Player player, Match match) {
        MatchPlayer matchPlayer = getMatchPlayer(player, match);

        List<String> wordList = matchPlayer.getWords();
        if(wordList == null || wordList.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean allPlayersResponded(Match match) {
        for(MatchPlayer matchPlayer: match.getPlayers()) {
            List<String> wordList = matchPlayer.getWords();
            if(wordList == null || wordList.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public void setResponse(Player player, Match match, List<String> wordList) {
        MatchPlayer matchPlayer = getMatchPlayer(player, match);

        Integer score = calculateScore(match, wordList);

        matchPlayer.setScore(score);
        matchPlayer.setWords(wordList);

        if(allPlayersResponded(match)) {
            match.setStatus(Match.Status.FINISHED);
        }
    }

    public MatchPlayer getMatchPlayer(Player player, Match match) {
        for(MatchPlayer matchPlayer: match.getPlayers()) {
            if(matchPlayer.getPlayer().equals(player)) {
                return matchPlayer;
            }
        }

        return null;
    }

    public MatchPlayer getMatchPlayer(String playerId, Match match) {
        for(MatchPlayer matchPlayer: match.getPlayers()) {
            if(matchPlayer.getPlayer().getId().equals(playerId)) {
                return matchPlayer;
            }
        }

        return null;
    }

    // TODO: calculateScore
    private Integer calculateScore(Match match, List<String> words) {
        return 99;
    }
}
