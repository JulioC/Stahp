package br.com.thejcs.stahp.server.persistence.dao;

import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import org.springframework.stereotype.Repository;

@Repository
public class MatchPlayerDAOImpl extends BaseDAO<MatchPlayer, Integer> implements MatchPlayerDAO {
}
