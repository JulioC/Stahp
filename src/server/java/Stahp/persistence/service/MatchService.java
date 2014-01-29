package Stahp.persistence.service;

import Stahp.persistence.model.Match;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MatchService {

    public void save(Match match);

    public List<Match> findAll();

    public Match findById(String id);

    public List<Match> search(ISearch search);

    public void delete(String id);

}