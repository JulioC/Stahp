package br.com.thejcs.stahp.server.game;

import br.com.thejcs.stahp.server.persistence.model.Challenge;

import java.util.ArrayList;
import java.util.List;

public class DummyChallengeSelector implements ChallengeSelector {

    @Override
    public List<Challenge> generateList(Integer size) {
        return new ArrayList<Challenge>();
    }

}
