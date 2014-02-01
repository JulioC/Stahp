package Stahp.entity;

import Stahp.persistence.model.Challenge;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChallengeEntity {

    private String topic;

    private String initial;

    public ChallengeEntity() {
    }

    public ChallengeEntity(Challenge challenge) {
        this.topic = challenge.getTopic().getDescription();
        this.initial = challenge.getInitial().toString();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }
}
