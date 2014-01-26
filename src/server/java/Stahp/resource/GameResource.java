package Stahp.resource;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class GameResource {

    private Integer id;

    private String state;

    private List<PlayerResource> playerEntities;

    public GameResource() {
        playerEntities = new ArrayList<PlayerResource>();
    }

    public GameResource(Integer id, String state) {
        this.id = id;
        this.state = state;

        playerEntities = new ArrayList<PlayerResource>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<PlayerResource> getPlayerEntities() {
        return playerEntities;
    }

    public void setPlayerEntities(List<PlayerResource> playerEntities) {
        this.playerEntities = playerEntities;
    }
}
