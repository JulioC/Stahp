package br.com.thejcs.stahp.server.persistence.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    private Set<MatchPlayer> matches = new HashSet<MatchPlayer>();

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

    public Set<MatchPlayer> getMatches() {
        return matches;
    }
}
