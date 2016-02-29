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
        Optional<UserAccount> account = userRepository.findUserAccountByName(username);
        String login=account.map(UserAccount::getName).orElse("");
        String password=account.map(UserAccount::getHash).orElse("");
        String role=account.map(UserAccount::getRole).get().toString();
        return new User(login, password, AuthorityUtils.createAuthorityList(role));
    }

}