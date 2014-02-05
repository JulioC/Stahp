package br.com.thejcs.stahp.server.persistence.service;

import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MatchPlayerService {

    public List<MatchPlayer> findByPlayer(Player player);

}
