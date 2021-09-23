package com.rony.responseDto;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import com.rony.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerRespDto {
    private String id;
    private PlayerStatus playerStatus;

    private PlayerExpertise expertise;

    private String userName;

}
