package com.rony.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team extends BaseModel{

    private String name;

    @OneToOne
    private Country country;

    @OneToMany
    @JoinTable(name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")})
    private List<Player> playerList;

    @OneToOne
    private User coach;

    /**
     * INSERT INTO `icc_spring`.`users`
     * (`id`, `age`, `email`, `gender`, `homeTown`, `mobile`, `name`, `password`, `role`)
     * VALUES ('100', '123', 'coach-email', 'M', 'Dhaka', '1234', 'coach-name', '1234', '2');
     */
}
