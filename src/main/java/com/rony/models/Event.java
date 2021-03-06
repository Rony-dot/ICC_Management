package com.rony.models;


import com.rony.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event extends BaseModel{

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDateTime;

    private EventType eventType;

    @OneToOne
    private Team team1;

    @OneToOne
    private Player team1Captain;

    @OneToOne
    private Team team2;

    @OneToOne
    private Player team2Captain;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "umpires_in_event",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "umpire_id")})
    private List<User> umpires;

    @Column(nullable = true)
    private int score;

//    private CountryReqDto
//    @ManyToOne
//    private CountryManager countryManager;
}
