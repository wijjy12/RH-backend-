package com.group.rh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "our_users")
public class OurUsers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
    private Admin admin;

    @JsonBackReference(value = "user_emp")
    @OneToOne(mappedBy = "user")
    private Employe employe;

    @JsonBackReference(value = "user_emplh")
    @OneToOne(mappedBy = "user")
    private EmployeRH employeRH;

    @JsonBackReference(value = "user_cand")
    @OneToOne(mappedBy = "user")
    private Candidat candidat;
    private String name;
   // DU COUP tu as fais un ENUM pour le role ?OUIII; d'accord
   @Override
   public String toString() {
       return "OurUsers{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", role=" + role +
               '}';
   }
    public enum Role {
        ADMIN,
        EMPLOYE,
        EMPLOYERH,
        CANDIDAT
    }

    public OurUsers( String email, String password, Role role, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
