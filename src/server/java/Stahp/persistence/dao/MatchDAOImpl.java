package Stahp.persistence.dao;

import Stahp.persistence.model.Match;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDAOImpl extends BaseDAO<Match, String> implements MatchDAO {
}
