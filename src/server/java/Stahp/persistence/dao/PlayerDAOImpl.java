package Stahp.persistence.dao;

import Stahp.persistence.model.Player;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAOImpl extends BaseDAO<Player, String> implements PlayerDAO {
}
