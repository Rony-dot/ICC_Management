package com.rony.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Player_Statistics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerStatistics extends BaseModel{

    private int ball_played;
    private int dead_Ball;
    private int fours;
    private int sixes;
    private int maiden;
    private int no_Ball;
    private int score;
    private int total_balled;
    private int wicket_taken;
    private int wide_ball;

    @OneToOne
    private Player player;
    @OneToOne
    private Event event;
}
