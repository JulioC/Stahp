package Stahp.persistence.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Date created;

    private Date updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator")
    private Player creator;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
//    private Set<GamePlayer> players = new HashSet<GamePlayer>();

    public Game() {
    }

    public Game(Player creator) {
        this.creator = creator;
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

//    public Set<GamePlayer> getPlayers() {
//        return players;
//    }
}
