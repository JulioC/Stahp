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

@Path("players")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PlayerController {

    private final Logger logger = Logger.getLogger(GameController.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @POST
    public PlayerResource newPlayer(
            @NotNull @QueryParam("name") String name) {
        Player player = new Player(name);

        try {
            playerService.save(player);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return new PlayerResource(player);
    }

    @GET
    @Path("{uuid}")
    public PlayerResource getPlayer(
            @PathParam("uuid") String uuid) {
        Player player;
        try {
            player = playerService.findById(uuid);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return new PlayerResource(player);
    }


}
