package Stahp.game;

import Stahp.persistence.model.Challenge;

import java.util.List;

/**
 * Created by Júlio on 29/01/14.
 */
public interface ChallengeSelector {

    public List<Challenge> generateList(Integer size);

}
