package com.example.SmartRestaurant.config.userdetail;

import com.example.SmartRestaurant.common.UserStatus;
import com.example.SmartRestaurant.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserEntity userEntity;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    public UserEntity getUser() {
        return this.userEntity;
    }

    @Override
    public String getPassword() {
        if (userEntity == null)
            return null;
        if (userEntity.getPassword() == null)
            return null;
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        if (userEntity == null)
            return null;
        if (userEntity.getPhoneNumber() == null)
            return null;
        return userEntity.getPhoneNumber();
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
        return userEntity.getStatus() == UserStatus.ACTIVE;
    }
}
