package Stahp.persistence.service;

import Stahp.persistence.dao.GameDAO;
import Stahp.persistence.dto.Game;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    GameDAO dao;

    @Autowired
    public void setDao(GameDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Game game) {
        dao.save(game);
    }

    @Override
    public List<Game> findAll() {
        return dao.findAll();
    }

    @Override
    public Game findById(String id) {
        return dao.find(id);
    }

    @Override
    public List<Game> search(ISearch search) {
        return dao.search(search);
    }

    @Override
    public void delete(String id) {
        dao.removeById(id);
    }
}
