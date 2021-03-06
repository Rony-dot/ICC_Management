package com.rony.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rony.enums.Countries;
import com.rony.enums.UserRole;
import lombok.*;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"country"})
@Getter
@Setter
@EqualsAndHashCode
public class User extends BaseModel implements UserDetails, Serializable {

    @NotNull(message = "name cannot be null")
    @Size(min = 4, max = 10, message
            = "Name must be between 4 and 10 characters")
    private String name;

//    private UserRole role;

    @OneToOne
    @JoinColumn(name = "user_role")
    private Role userRole;

    private String age;

    @Email(message = "email invalid")
    @NotNull(message = "email cannot be empty")
    @Column(unique = true)
    private String email;

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "number is required")
    private String mobile;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "salutation is required")
    private String salutation;

    @NotNull(message = "hometown is required")
    private String homeTown;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;


//    @NotNull(message = "country must be given")
//    private Countries country;

    // Collection<? extends GrantedAuthority> => means in the ? mark, it expects any class that implements GrantedAuthority
    // Note: Here Role class implements GrantedAuthority, so no errors
    // in the place of " Collection<? extends GrantedAuthority " we can directly write List<Role>,
    // because our Role class implements GrantedAuthority as per the rules
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(this.userRole);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(this.userRole);
        return userRoles;
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
