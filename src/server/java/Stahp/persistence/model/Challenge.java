package Stahp.persistence.model;

import javax.persistence.*;

@Entity
@Table(name="challenges")
public class Challenge {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Topic topic;

    private Character initial;

    public Topic getTopic() {
        return topic;
    }

    public Character getInitial() {
        return initial;
    }
}
