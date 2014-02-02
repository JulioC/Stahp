package br.com.thejcs.stahp.server.persistence.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="match_player")
public class MatchPlayer {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

    private Integer score;

    // TODO: these words should have some stats about it's score
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name="match_player_words", joinColumns=@JoinColumn(name="match_player"))
    @Column(name="word")
    private List<String> words;

    public MatchPlayer() {

    }

    public MatchPlayer(Match match, Player player) {
        this.match = match;
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MatchPlayer) {
            MatchPlayer matchPlayer = (MatchPlayer) obj;

            if(!matchPlayer.player.equals(player)) {
                return false;
            }
            if(!matchPlayer.match.equals(match)) {
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

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
