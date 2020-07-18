package com.example.app_site.controller;

import com.example.app_site.domain.Role;
import com.example.app_site.domain.User;
import com.example.app_site.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{userId}")
    public String userEditForm(@PathVariable Long userId, Model model) {
        User user = userRepo.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam Long userId
    ) {
        User user = userRepo.findById(userId).orElse(null);
        user.setUsername(username);

        addRolesToUser(form, user);

        userRepo.save(user);
        return "redirect:/user";
    }

    private static void addRolesToUser(@RequestParam Map<String, String> form, User user) {
        Set<String> allPossibleRoles = Arrays.stream(Role.values())
                .map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        form.keySet().stream().filter(allPossibleRoles::contains)
                .forEach(newKey -> user.getRoles().add(Role.valueOf(newKey)));
    }
}
