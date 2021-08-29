package com.rony.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User extends BaseModel{

    @NotNull(message = "name cannot be null")
//    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4, max = 10, message
            = "Name must be between 4 and 10 characters")
    private String name;

//    private String age;
//    private String email;
//    private String password;
//    private String mobile;
//
//    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateOfBirth;
//    private String gender;
//    private String salutation;
//    private String homeTown;

}
