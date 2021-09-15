package com.rony.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseModel{

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managing_director")
    private User managingDirector;

    @ManyToMany(cascade = CascadeType.ALL)
//    @ManyToMany
    @JoinTable(name = "country_player",
                joinColumns = {@JoinColumn(name = "country_id", nullable = true) },
                inverseJoinColumns = {@JoinColumn(name = "player_id", unique = false, nullable = true)})
    private List<Player> playerList;

}
