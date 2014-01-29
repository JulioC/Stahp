package Stahp.persistence.dto;

import Stahp.game.MatchStatus;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="match")
public class Match {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Date created;

    private Date updated;

    private MatchStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Player creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<MatchPlayer> players = new HashSet<MatchPlayer>();

    public Match() {
    }

    public Match(Player creator) {
        this.creator = creator;
        this.status = MatchStatus.CREATED;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Player getCreator() {
        return creator;
    }

    public Set<MatchPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        MatchPlayer matchPlayer = new MatchPlayer(this, player);

//        player.getGames().add(matchPlayer);
        players.add(matchPlayer);
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }
}
