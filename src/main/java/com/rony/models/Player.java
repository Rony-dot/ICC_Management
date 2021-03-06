package com.rony.models;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player extends BaseModel{

    @Column(name = "player_status")
    private PlayerStatus playerStatus;

    private PlayerExpertise expertise;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userInfo;

}
