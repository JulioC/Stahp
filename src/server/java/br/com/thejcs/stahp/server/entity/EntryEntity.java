package br.com.thejcs.stahp.server.entity;

import br.com.thejcs.stahp.server.persistence.model.MatchPlayer;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class EntryEntity {

    private List<String> words;

    private PlayerEntity player;

    private Integer score;

    public EntryEntity() {
    }

    public EntryEntity(MatchPlayer matchPlayer) {
        this.words = matchPlayer.getWords();
        this.score = matchPlayer.getScore();
        this.player = new PlayerEntity(matchPlayer.getPlayer());
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
