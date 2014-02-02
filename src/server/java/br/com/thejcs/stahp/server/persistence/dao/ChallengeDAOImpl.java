package br.com.thejcs.stahp.server.persistence.dao;

import br.com.thejcs.stahp.server.persistence.model.Challenge;
import org.springframework.stereotype.Repository;

@Repository
public class ChallengeDAOImpl extends BaseDAO<Challenge, Integer> implements ChallengeDAO {
}
