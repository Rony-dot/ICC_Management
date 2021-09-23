package com.rony.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryReqDto {
    private String id;

    @NotNull(message = "name must be given")
    @Size(min = 3, max = 30, message = "must be greater than or equal 3 and less than or equal 30")
    private String name;

    @NotNull(message = "must select an country manager")
    private String countryManagerId;

    private List<Long> playerIds;

}
