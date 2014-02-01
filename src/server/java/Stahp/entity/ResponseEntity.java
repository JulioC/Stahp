package Stahp.entity;

import Stahp.persistence.model.MatchPlayer;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ResponseEntity {

    private List<String> words;

    private Integer score;

    public ResponseEntity() {
    }

    public ResponseEntity(MatchPlayer matchPlayer) {
        this.words = matchPlayer.getWords();
        this.score = matchPlayer.getScore();
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
}
