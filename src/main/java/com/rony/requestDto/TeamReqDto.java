package com.rony.requestDto;

import com.rony.models.BaseModel;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamReqDto {

    private String id;

    @NotNull(message = "name cannot be empty")
    @Size(min = 2, message = "min is 2 characters")
    private String name;

    private String countryId;

    private List<String> playerIdList;

    private String coachId;

    /**
     * INSERT INTO `icc_spring`.`users`
     * (`id`, `age`, `email`, `gender`, `homeTown`, `mobile`, `name`, `password`, `role`)
     * VALUES ('100', '123', 'coach-email', 'M', 'Dhaka', '1234', 'coach-name', '1234', '2');
     */
}
