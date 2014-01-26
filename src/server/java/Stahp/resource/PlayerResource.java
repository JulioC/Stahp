package Stahp.resource;

import Stahp.persistence.dto.Player;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerResource {

    private String uuid;

    private String name;

    public PlayerResource() {
    }

    public PlayerResource(Player player) {
        uuid = player.getUuid();
        name = player.getName();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
