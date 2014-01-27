package Stahp.persistence.dao;

import Stahp.persistence.dto.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAOImpl extends BaseDAO<Game, String> implements GameDAO {
}
