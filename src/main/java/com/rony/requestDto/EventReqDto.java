package com.rony.requestDto;


import com.rony.enums.EventType;
import com.rony.models.BaseModel;
import com.rony.models.Player;
import com.rony.models.Team;
import com.rony.models.User;
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
public class EventReqDto extends BaseModel {

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDateTime;

    private EventType eventType;
    private String idTeam1;
    private String idTeam1Cap;
    private String idTeam2;
    private String idTeam2Cap;
    private List<String> umpireIds;
    private String score;

}
