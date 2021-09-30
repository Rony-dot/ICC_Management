package com.rony.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team extends BaseModel{

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @JoinColumn(name = "coach_id")
    @OneToOne
    private User coach;

    // owner side is player, because that is the many side; so here using oneToMany will make the player side unique;
    // that is either specify the ManyToOne => relation in player class or,
    // in this class make => ManyToMany to avoid unique constraint;
    @ManyToMany
    @JoinTable(name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")})
    private List<Player> playerList;


    /**
     * INSERT INTO `icc_spring`.`users`
     * (`id`, `age`, `email`, `gender`, `homeTown`, `mobile`, `name`, `password`, `role`)
     * VALUES ('100', '123', 'coach-email', 'M', 'Dhaka', '1234', 'coach-name', '1234', '2');
     */

}
