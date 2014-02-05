package br.com.thejcs.stahp.server.persistence.service;

import br.com.thejcs.stahp.server.persistence.dao.MatchPlayerDAO;
import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;
import com.googlecode.genericdao.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatchPlayerServiceImpl implements MatchPlayerService {

    MatchPlayerDAO dao;

    @Autowired
    public void setDao(MatchPlayerDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<MatchPlayer> findByPlayer(Player player) {
        Search search = new Search();
        search.addFilterEqual("player", player);
        return dao.search(search);
    }
}
