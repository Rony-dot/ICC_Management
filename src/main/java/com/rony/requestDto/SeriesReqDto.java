package com.rony.requestDto;

import com.rony.enums.SeriesType;
import com.rony.models.BaseModel;
import com.rony.models.Event;
import com.rony.models.Team;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeriesReqDto extends BaseModel {

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesEndDate;
    private SeriesType seriesType;

    private List<String> eventIdList;

    private List<Team> participantTeams;
}
