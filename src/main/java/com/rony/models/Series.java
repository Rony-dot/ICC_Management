package com.rony.models;

import com.rony.enums.SeriesType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Series extends BaseModel{

    private String name;
    private LocalDateTime seriesStartDate;
    private SeriesType seriesType;

    @OneToMany
    @JoinTable(name = "events_in_series",
            joinColumns = {@JoinColumn(name = "series_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> eventList;


    @OneToMany
    @JoinTable(name = "participant_teams",
            joinColumns = {@JoinColumn(name = "series_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private List<Team> participantTeams;

    private LocalDateTime seriesEndDate;
}
