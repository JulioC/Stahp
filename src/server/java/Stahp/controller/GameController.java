package Stahp.controller;

import Stahp.persistence.dto.Player;
import Stahp.persistence.service.PlayerService;
import Stahp.resource.GameResource;
import Stahp.resource.PlayerResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("games")
public class GameController {

    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GameResource getJSON() {
        try {
            logger.info("Pre: " + playerService.findAll().size());

            Player p = new Player("test");
            playerService.save(p);

            List<Player> players = playerService.findAll();
            logger.info("Pos: " + players.size());
            for (Player player: players) {
                logger.debug(player.getUuid() + " "  + player.getName());
            }
        }
        catch (Exception e) {
            logger.error(e);
        }

        GameResource gameResource = new GameResource(1, "test");
        return gameResource;
    }
}
