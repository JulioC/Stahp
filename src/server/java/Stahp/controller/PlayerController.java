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

    /**
     * Create a new player
     *
     * @param name desired display name
     * @return PlayerResource for the player created
     */
    @POST
    public PlayerResource createPlayer(
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

    /**
     * Get a player's profile
     *
     * @param id UUID for player
     * @return PlayerResource for player profile
     */
    @GET
    @Path("{id}")
    public PlayerResource getPlayer(
            @PathParam("id") String id) {
        Player player;
        try {
            player = playerService.findById(id);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return new PlayerResource(player);
    }


}
