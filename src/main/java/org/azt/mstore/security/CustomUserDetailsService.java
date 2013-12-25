package org.azt.mstore.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.azt.mstore.dao.UserDao;
import org.azt.mstore.model.Role;
import org.azt.mstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = this.userDao.getUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword().toLowerCase(), true, true, true, true, getAuthorities(user.getId()));

        return userDetails;
    }

    private Collection<GrantedAuthority> getAuthorities(Integer userId) {

        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        List<Role> roles = this.userDao.getRolesByUserId(userId);

        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return auths;
    }

}
