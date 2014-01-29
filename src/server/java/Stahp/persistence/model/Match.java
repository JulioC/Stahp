package Stahp.persistence.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Player creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<MatchPlayer> players = new HashSet<MatchPlayer>();

    @ManyToMany(fetch = FetchType.LAZY)
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
