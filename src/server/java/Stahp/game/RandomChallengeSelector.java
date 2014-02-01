package Stahp.game;

import Stahp.persistence.model.Challenge;
import Stahp.persistence.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomChallengeSelector implements ChallengeSelector {
    private ChallengeService challengeService;

    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Override
    public List<Challenge> generateList(Integer size) {
        List<Challenge> list = challengeService.findAll();

        Random rand = new Random();
        int n = list.size();

        ArrayList<Challenge> selection = new ArrayList<Challenge>();
        for(int i = 0; i < size; ++i) {
            int r = rand.nextInt(n);
            selection.add(list.get(r));
        }

        return list;
    }
}
