package Stahp.resource;


import Stahp.entity.PlayerEntity;
import Stahp.persistence.model.Player;
import Stahp.persistence.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("players")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PlayerResource {

    private final Logger logger = Logger.getLogger(PlayerResource.class.getName());

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Context
    private UriInfo uriInfo;

    /**
     * Create a new currentPlayer
     *
     * @param name desired display name
     * @return Response 201 with created currentPlayer location
     */
    @POST
    public Response createPlayer(
            @NotNull @FormParam("name") String name) {
        Player player = new Player(name);

        try {
            playerService.save(player);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI createdUri = ub.
                path(player.getId()).
                build();
        return Response.created(createdUri).build();
    }

    /**
     * Get a currentPlayer's profile
     *
     * @param id UUID for currentPlayer
     * @return PlayerEntity for currentPlayer profile
     */
    @GET
    @Path("{id}")
    public PlayerEntity getPlayer(
            @PathParam("id") String id) {
        Player player;
        try {
            player = playerService.findById(id);
        }
        catch (Exception e) {
            logger.error(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if(player == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return new PlayerEntity(player);
    }


}
