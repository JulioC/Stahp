package Stahp.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class GameEntity {

    private String id;

    private String state;

    private List<PlayerEntity> playerEntities;

    public GameEntity() {
        playerEntities = new ArrayList<PlayerEntity>();
    }

    public GameEntity(String id, String state) {
        this.id = id;
        this.state = state;

        playerEntities = new ArrayList<PlayerEntity>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<PlayerEntity> getPlayerEntities() {
        return playerEntities;
    }

    public void setPlayerEntities(List<PlayerEntity> playerEntities) {
        this.playerEntities = playerEntities;
    }
}
