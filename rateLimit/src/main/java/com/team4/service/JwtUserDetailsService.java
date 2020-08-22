package com.team4.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    /**
     * Method to validate the user.
     *
     * @param username username given.
     * @return User object with both username and encrypted password.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user1".equals(username)) {
            return new User("user1", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else if ("user2".equals(username)) {
            return new User("user2", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            return new User("default", "$2a$10$XMjlkmfvXNm/LSfqeVD4gOKjtuvX7FZiBktIhBflaGhu39ws1kwle",
                    new ArrayList<>());
        }
    }

}