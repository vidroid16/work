package com.example.demo.services.implementations;

import com.example.demo.database.entity.Account;
import com.example.demo.database.repo.AccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public AccountServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AccountRepository accountRepository) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public String login(String username, String password) {
        String tok;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Account user = accountRepository.findByUsername(username);
            String token = jwtTokenProvider.createToken(username, user.getRole().name());
            tok = token;
        } catch (AuthenticationException e) {
            return "bad";
        }
        return tok;
    }
}
