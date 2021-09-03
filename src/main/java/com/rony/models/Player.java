package com.rony.models;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player extends BaseModel{

    private String name;

//    @ManyToOne
//    private Team team;

    @Column(name = "player_status")
    private PlayerStatus playerStatus;

    private PlayerExpertise expertise;

    private boolean isCaptain;

}
