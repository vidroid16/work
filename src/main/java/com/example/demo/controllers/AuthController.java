package com.example.demo.controllers;

import com.example.demo.database.entity.Account;
import com.example.demo.database.repo.AccountRepository;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        private AccountService accountService;
        private AccountRepository accountRepository;

        @Autowired
        public AuthController(AccountService accountService, AccountRepository accountRepository) {
            this.accountService = accountService;
            this.accountRepository = accountRepository;
        }

        @PostMapping("/login")
        public ResponseEntity login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
            String req = accountService.login(username,password);
            if (!req.equals("bad")) {
                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("token", req);
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
            }
        }

        @PostMapping("/logout")
        public void logout(HttpServletRequest request, HttpServletResponse response) {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, response, null);
        }
        @PostMapping("/save")
        public ResponseEntity saves(){
            try {
                accountRepository.save(new Account("admin","admin"));
                return ResponseEntity.ok().build();
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }

    }
