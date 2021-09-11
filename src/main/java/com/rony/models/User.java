package com.rony.models;


import com.rony.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{

    @NotNull(message = "name cannot be null")
//    @NotEmpty(message = "name cannot be empty")
//    @Size(min = 4, max = 10, message
//            = "Name must be between 4 and 10 characters")
    private String name;

    private UserRole role;

    private String age;
    @Column(unique = true)
    private String email;
    private String password;
    private String mobile;
//
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String salutation;
    private String homeTown;

}
