package com.example.sweater.service;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${myhostname}")
    private String hostname;

    public UserService(UserRepo userRepo, MailService mailService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        setPasswordCr(user);
        sendMessage(user);
        userRepo.save(user);
        return true;

    }

    private void setPasswordCr(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s \n" + "Welcome to Sweater. " +
                            "Please, visit next link: http://%s/activate/%s",
                    user.getUsername(), hostname, user.getActivationCode());
            mailService.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String str : form.keySet()) {
            if (roles.contains(str)) {
                user.getRoles().add(Role.valueOf(str));
            }
            userRepo.save(user);
        }

    }

    public void updateAccount(User user, String password, String email) {
        log.info("update account");
        boolean isEmailChanged = (user.getEmail() != null && !user.getEmail().equals(email)) ||
                (email != null && !email.equals(user.getEmail()));

        if (isEmailChanged) {
            if (!StringUtils.isEmpty(email)) {
                log.info("update email adress");
                user.setEmail(email);
                log.info("after send+ {}", user.getEmail());
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
            setPasswordCr(user);
        }

        userRepo.save(user);
        if (isEmailChanged) {
            if (!StringUtils.isEmpty(email)) {
                sendMessage(user);
            }
        }
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepo.save(user);
    }
}
