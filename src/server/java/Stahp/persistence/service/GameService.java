package Stahp.persistence.service;

import Stahp.persistence.dto.Game;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GameService {

    public void save(Game game);

    public List<Game> findAll();

    public Game findById(String id);

    public List<Game> search(ISearch search);

    public void delete(String id);

}