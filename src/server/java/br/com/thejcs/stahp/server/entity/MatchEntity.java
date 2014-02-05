package br.com.thejcs.stahp.server.entity;

import br.com.thejcs.stahp.server.persistence.model.Match;
import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;
import br.com.thejcs.stahp.server.persistence.model.Player;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class MatchEntity {

    private String id;

    private String status;

    private Date created;

    private Date updated;

    private PlayerEntity creator;

    private Integer timeLimit;

    private ArrayList<EntryEntity> entries = new ArrayList<EntryEntity>();

    private boolean responded;
    private boolean joined;

    public MatchEntity() {
    }

    public MatchEntity(Match match) {
        populateFields(match, null);
    }

    public MatchEntity(Match match, Player player) {
        populateFields(match, player);
    }

    public void populateFields(Match match, Player player) {
        this.id = match.getId();
        this.status = match.getStatus().name();
        this.creator = new PlayerEntity(match.getCreator());

        this.created = match.getCreated();
        this.updated = match.getUpdated();

        this.timeLimit = match.getTimeLimit();

        this.joined = false;
        this.responded = false;

        for(MatchPlayer matchPlayer: match.getPlayers()) {
            entries.add(new EntryEntity(matchPlayer));

            if(player != null) {
                if(matchPlayer.getPlayer().equals(player)) {
                    this.joined = true;
                    this.responded = (matchPlayer.getScore() != null);
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlayerEntity getCreator() {
        return creator;
    }

    public void setCreator(PlayerEntity creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public ArrayList<EntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<EntryEntity> entries) {
        this.entries = entries;
    }
}
