package Stahp.resource;

import Stahp.persistence.dto.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerResource {

    private String id;

    private String name;

    public PlayerResource() {
    }

    public PlayerResource(Player player) {
        id = player.getId();
        name = player.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
