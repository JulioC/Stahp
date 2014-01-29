package Stahp.persistence.service;

import Stahp.persistence.dao.ChallengeDAO;
import Stahp.persistence.model.Challenge;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService {

    ChallengeDAO dao;

    @Autowired
    public void setDao(ChallengeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Challenge> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Challenge> search(ISearch search) {
        return dao.search(search);
    }

}
