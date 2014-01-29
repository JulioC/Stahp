package Stahp.persistence.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
//    private Set<MatchPlayer> games = new HashSet<MatchPlayer>();

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<MatchPlayer> getGames() {
//        return games;
//    }
}
