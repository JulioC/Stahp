package Stahp.resource;

import Stahp.persistence.dto.Game;
import Stahp.persistence.dto.Player;
import Stahp.persistence.service.GameService;
import org.apache.log4j.Logger;

public class GameWordResource {
    private final Logger logger = Logger.getLogger(GameWordResource.class.getName());

    private GameService gameService;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    private Game game;
    private Player currentPlayer;

    public GameWordResource(Player currentPlayer, Game game) {
        this.currentPlayer = currentPlayer;
        this.game = game;
    }

    // TODO: implement word list related methods
}
