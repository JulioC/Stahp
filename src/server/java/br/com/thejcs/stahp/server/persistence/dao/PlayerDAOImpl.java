package br.com.thejcs.stahp.server.persistence.dao;

import br.com.thejcs.stahp.server.persistence.model.Player;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAOImpl extends BaseDAO<Player, String> implements PlayerDAO {
}
