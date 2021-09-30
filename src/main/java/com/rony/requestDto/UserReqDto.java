package com.rony.requestDto;

import com.rony.enums.UserRole;
import com.rony.models.BaseModel;
import com.rony.models.Country;
import com.rony.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto {

    private String id;

    @NotNull(message = "name cannot be null")
    @Size(min = 4, max = 10, message
            = "Name must be between 4 and 10 characters")
    private String name;

    private String userRole;

    @Email(message = "email invalid")
    @NotNull(message = "email cannot be empty")
    private String email;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "number is required")
    private String mobile;

    private String age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private  String dateOfBirth;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "salutation is required")
    private String salutation;

    private String countryId;

    @NotNull(message = "hometown is required")
    private String homeTown;


}
