package Stahp.persistence.service;

import Stahp.persistence.dao.PlayerDAO;
import Stahp.persistence.model.Player;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    PlayerDAO dao;

    @Autowired
    public void setDao(PlayerDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Player player) {
        dao.save(player);
    }

    @Override
    public List<Player> findAll() {
        return dao.findAll();
    }

    @Override
    public Player findById(String id) {
        return dao.find(id);
    }

    @Override
    public List<Player> search(ISearch search) {
        return dao.search(search);
    }

    @Override
    public void delete(String id) {
        dao.removeById(id);
    }
}
