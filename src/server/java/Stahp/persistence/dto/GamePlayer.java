package Stahp.persistence.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game_player")
public class GamePlayer {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="PLAYER")
//    private Player player;
//
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="GAME")
//    private Game game;

}
