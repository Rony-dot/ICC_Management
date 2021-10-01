package com.rony.models;

import com.rony.enums.SeriesType;
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
@Table(name = "series")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Series extends BaseModel{

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesEndDate;
    private SeriesType seriesType;

    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(name = "events_in_series",
            joinColumns = {@JoinColumn(name = "series_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> eventList;

    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(name = "participant_teams",
            joinColumns = {@JoinColumn(name = "series_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private List<Team> participantTeams;
}
