package br.com.thejcs.stahp.server.persistence.service;

import br.com.thejcs.stahp.server.persistence.dao.MatchDAO;
import br.com.thejcs.stahp.server.persistence.model.Match;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

    MatchDAO dao;

    @Autowired
    public void setDao(MatchDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Match match) {
        dao.save(match);
    }

    @Override
    public List<Match> findAll() {
        return dao.findAll();
    }

    @Override
    public Match findById(String id) {
        return dao.find(id);
    }

    @Override
    public List<Match> search(ISearch search) {
        return dao.search(search);
    }

    @Override
    public void delete(String id) {
        dao.removeById(id);
    }
}
