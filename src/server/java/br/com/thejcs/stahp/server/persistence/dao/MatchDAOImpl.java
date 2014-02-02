package br.com.thejcs.stahp.server.persistence.dao;

import br.com.thejcs.stahp.server.persistence.model.Match;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDAOImpl extends BaseDAO<Match, String> implements MatchDAO {
}
