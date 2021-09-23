package com.rony.requestDto;

import com.rony.enums.PlayerExpertise;
import com.rony.enums.PlayerStatus;
import com.rony.models.BaseModel;
import com.rony.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerReqDto extends BaseModel {

    private PlayerStatus playerStatus;

    private PlayerExpertise expertise;

    private String userId;

}
