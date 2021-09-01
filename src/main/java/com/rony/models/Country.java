package com.rony.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseModel{

    private String name;

    @OneToOne
    @JoinColumn(name = "managing_director")
    private User managingDirector;

    @OneToMany
    @JoinTable(name = "country_player",
                joinColumns = {@JoinColumn(name = "country_id")},
                inverseJoinColumns = {@JoinColumn(name = "player_id")})
    private List<User> playerList;

}
