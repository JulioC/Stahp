package Stahp.entity;

import Stahp.persistence.dto.Match;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class MatchEntity {

    private String id;

    private String state;

    private List<PlayerEntity> playerEntities;

    public MatchEntity() {
        playerEntities = new ArrayList<PlayerEntity>();
    }

    public MatchEntity(Match match) {
        this.id = match.getId();
        this.state = "null";

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
