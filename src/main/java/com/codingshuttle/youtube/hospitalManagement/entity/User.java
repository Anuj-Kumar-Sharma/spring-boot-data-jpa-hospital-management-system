package com.codingshuttle.youtube.hospitalManagement.entity;


import com.codingshuttle.youtube.hospitalManagement.entity.type.RoleType;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @JoinColumn(unique = true)
    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType> roleTypes = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();
    }
}
