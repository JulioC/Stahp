package Stahp.persistence.service;


import Stahp.persistence.model.Challenge;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ChallengeService {

    public List<Challenge> findAll();

    public List<Challenge> search(ISearch search);

}
