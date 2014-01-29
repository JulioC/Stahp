package Stahp.persistence.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="match_player")
public class MatchPlayer {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    private Player player;

    @ManyToOne(fetch=FetchType.LAZY)
    private Match match;

    private Integer score;

    public MatchPlayer(Match match, Player player) {
        this.match = match;
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MatchPlayer) {
            MatchPlayer matchPlayer = (MatchPlayer) obj;

            if(matchPlayer.player != player) {
                return false;
            }
            if(matchPlayer.match != match) {
                return false;
            }

            return true;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(match, player);
    }
}
