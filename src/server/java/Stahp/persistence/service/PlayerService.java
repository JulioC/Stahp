package Stahp.persistence.service;

import Stahp.persistence.model.Player;
import com.googlecode.genericdao.search.ISearch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PlayerService {

    public void save(Player player);

    public List<Player> findAll();

    public Player findById(String id);

    public List<Player> search(ISearch search);

    public void delete(String id);

}
