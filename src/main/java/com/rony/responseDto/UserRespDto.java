package com.rony.responseDto;

import com.rony.enums.UserRole;
import com.rony.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDto {

    private String id;

    @NotNull(message = "name cannot be null")
    @Size(min = 4, max = 10, message
            = "Name must be between 4 and 10 characters")
    private String name;

    private UserRole role;


    private String  userRole;

    private String age;

    @Email(message = "email invalid")
    @NotNull(message = "email cannot be empty")
    private String email;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "number is required")
    private String mobile;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private String dateOfBirth;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "salutation is required")
    private String salutation;

    @NotNull(message = "hometown is required")
    private String homeTown;

    private String countryName;

}
