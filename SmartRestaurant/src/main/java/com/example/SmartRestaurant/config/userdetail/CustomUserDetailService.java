package com.example.SmartRestaurant.config.userdetail;

import com.example.SmartRestaurant.dto.response.UserResponseDetail;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailService implements UserDetailsService {
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        UserResponseDetail user = userService.getByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UserNotFoundException();
        }
        Collection<SimpleGrantedAuthority> authorities = Stream.concat(
                user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x)),
                user.getPermissions().stream().map(x -> new SimpleGrantedAuthority(x))).toList();
        CustomUserDetails userDetails = new CustomUserDetails(user.getId()
                , user.getPhoneNumber()
                , user.getPassword()
                , user.getStatus()
                , authorities
        );
        return userDetails;
    }
}
