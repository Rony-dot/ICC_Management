package com.rony.responseDto;

import com.rony.enums.SeriesType;
import com.rony.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeriesRespDto{
    private String id;
    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date seriesEndDate;

    private SeriesType seriesType;

    private List<String> eventNameList;

    private List<String> participantTeamNames;
}
