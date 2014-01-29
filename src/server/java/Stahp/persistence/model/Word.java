package Stahp.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="words")
public class Word {
    public static enum Quality {
        COMMON,
        RARE
    }

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    private Challenge challenge;

    private String word;

    @Enumerated(EnumType.STRING)
    private Quality quality;

    public Challenge getChallenge() {
        return challenge;
    }

    public String getWord() {
        return word;
    }

    public Quality getQuality() {
        return quality;
    }
}
