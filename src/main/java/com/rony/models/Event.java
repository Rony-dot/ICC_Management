package com.rony.models;

import com.rony.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event extends BaseModel{

    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private EventType eventType;

    @OneToOne
    private Team team1;
    @OneToOne
    private Team team2;

    @OneToMany
    @JoinTable(name = "umpires_in_event",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "umpire_id")})
    private List<User> umpires;

    private int score;

//    private Country
//    @ManyToOne
//    private CountryManager countryManager;
}
