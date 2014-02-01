package Stahp.persistence.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="players")
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player) {
            Player player = (Player) obj;

            if(!player.id.equals(id)) {
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
