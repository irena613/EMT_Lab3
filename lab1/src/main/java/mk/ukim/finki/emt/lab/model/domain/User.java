package mk.ukim.finki.emt.lab.model.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.lab.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@Table(name = "library_member")
//@NamedEntityGraph(
//    name = "user-without-wishlist",
//    attributeNodes = {
//        @NamedAttributeNode("username"),
//        @NamedAttributeNode("password"),
//        @NamedAttributeNode("name"),
//        @NamedAttributeNode("surname"),
//        @NamedAttributeNode("isAccountNonExpired"),
//        @NamedAttributeNode("isAccountNonLocked"),
//        @NamedAttributeNode("isCredentialsNonExpired"),
//        @NamedAttributeNode("isEnabled"),
//        @NamedAttributeNode("role")
//    }
//)
//@NamedEntityGraph(
//    name = "user-with-wishlist",
//    attributeNodes = {
//        @NamedAttributeNode("username"),
//        @NamedAttributeNode("password"),
//        @NamedAttributeNode("name"),
//        @NamedAttributeNode("surname"),
//        @NamedAttributeNode("isAccountNonExpired"),
//        @NamedAttributeNode("isAccountNonLocked"),
//        @NamedAttributeNode("isCredentialsNonExpired"),
//        @NamedAttributeNode("isEnabled"),
//        @NamedAttributeNode("role"),
//        @NamedAttributeNode("wishlists")
//    }
//)
public class User implements UserDetails {

    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Wishlist wishlists;

    public User() {
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_USER;
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }



}