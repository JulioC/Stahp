package Stahp.controller;


import Stahp.persistence.dto.Player;
import Stahp.persistence.service.PlayerService;
import Stahp.resource.PlayerResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("players")
public class PlayerController {

    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PlayerResource newPlayer(
            @NotNull @QueryParam("name") String name) {
        Player player = new Player(name);

        try {
            playerService.save(player);

            List<Player> players = playerService.findAll();
            for (Player p: players) {
                logger.debug(p.getUuid() + " "  + p.getName());
            }
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        PlayerResource playerResource = new PlayerResource();
        playerResource.setUuid(player.getUuid());
        playerResource.setName(player.getName());
        return playerResource;
    }

}
