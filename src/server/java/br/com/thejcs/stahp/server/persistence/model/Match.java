package br.com.thejcs.stahp.server.persistence.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="matches")
public class Match {
    public static enum Status {
        CREATED,
        STARTED,
        FINISHED
    }

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Date created;

    private Date updated;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer timeLimit;

    @ManyToOne
    private Player creator;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "match")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<MatchPlayer> players = new HashSet<MatchPlayer>();

    @ManyToMany(fetch=FetchType.EAGER)
    private List<Challenge> challengeList;

    public Match() {
    }

    public Match(Player creator, Integer timeLimit) {
        this.creator = creator;
        this.timeLimit = timeLimit;

        this.status = Status.CREATED;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Match) {
            Match match = (Match) obj;

            if(!match.id.equals(id)) {
                return false;
            }

            return true;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Integer getTimeLimit() {
        return timeLimit;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Challenge> getChallengeList() {
        return challengeList;
    }

    public void setChallengeList(List<Challenge> challengeList) {
        this.challengeList = challengeList;
    }
}
