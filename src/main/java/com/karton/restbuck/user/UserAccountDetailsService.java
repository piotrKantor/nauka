package com.karton.restbuck.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userAccountDetailsService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAccountDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userRepository
                .findUserAccountByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("username: " + username));
        String login=account.getName();
        String password=account.getHash();
        String role=account.getRole().toString();
        return new User(login, password, AuthorityUtils.createAuthorityList(role));
    }

}