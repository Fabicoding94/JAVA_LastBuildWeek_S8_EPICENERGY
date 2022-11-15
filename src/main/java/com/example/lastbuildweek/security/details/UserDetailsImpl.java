package com.example.java_venerdi_s7.security.details;


import com.example.java_venerdi_s7.entities.Sonda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = false;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private Date ExpirationTime;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl( Long id, String username, String password, boolean enabled,
                            Collection<? extends GrantedAuthority> authorities ) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountNonLocked = enabled;
        this.accountNonExpired = enabled;
        this.credentialsNonExpired = enabled;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build( Sonda sonda ) {
        List<GrantedAuthority> authorities = sonda.getRoles()
                .stream()
                .map( role -> new SimpleGrantedAuthority( role.getRoleType().name() ) )
                .collect( Collectors.toList() );
        return new UserDetailsImpl( sonda.getId(), sonda.getUsername(), sonda.getPassword(),
                sonda.getActive(), authorities );
    }

}
