package com.tlz.BudgetingArc.service;

import com.tlz.BudgetingArc.domain.Authority;
import com.tlz.BudgetingArc.domain.User;
import com.tlz.BudgetingArc.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        user.setAuthorities(authorities);
        user = userRepository.save(user);

        return user;
    }

}
