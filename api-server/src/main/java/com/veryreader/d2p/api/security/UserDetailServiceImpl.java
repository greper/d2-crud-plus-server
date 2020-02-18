package com.veryreader.d2p.api.security;

import com.veryreader.d2p.api.modules.usersphere.entity.User;
import com.veryreader.d2p.api.modules.usersphere.service.UserService;
import com.veryreader.d2p.api.security.vo.LoginUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username, true);
        return new LoginUser(user);
    }
}