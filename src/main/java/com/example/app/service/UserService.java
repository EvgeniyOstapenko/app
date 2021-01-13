package com.example.app.service;

import com.example.app.domain.Role;
import com.example.app.domain.User;
import com.example.app.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) return false;

        user.setActive(true);

        if (user.getUsername().equals("admin")) {
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.ADMIN);
            user.setRoles(roles);
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        return true;
    }

    public void saveUser(User user, String editedUsername, Map<String, String> form) {
        user.setUsername(editedUsername);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String editedPassword, String editedEmail) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (editedEmail != null && !editedEmail.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(editedEmail));

        if (isEmailChanged) {
            user.setEmail(editedEmail);

        }

        if (!StringUtils.isEmpty(editedPassword)) {
            user.setPassword(passwordEncoder.encode(editedPassword));
        }
        userRepo.save(user);

        if (isEmailChanged) {
        }
    }

}
