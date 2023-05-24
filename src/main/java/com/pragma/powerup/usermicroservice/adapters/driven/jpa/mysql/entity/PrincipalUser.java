package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {

    private Long id;
    private String name;
    private String dni;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(Long id, String name, String dni, String userName, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id=id;
        this.name = name;
        this.dni = dni;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(UserEntity user, List<RoleEntity> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
        return new PrincipalUser(user.getId(), user.getName(), user.getDni(), user.getEmail(),
                user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "PrincipalUser{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
