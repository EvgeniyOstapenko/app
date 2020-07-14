package com.example.app.controller;

import com.example.app.domain.Role;
import com.example.app.domain.User;
import com.example.app.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
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

    @PostMapping("{userId}")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam Long userId
    ){

        User user = userRepo.findById(userId).orElse(null);
        user.setUsername(username);
        toAddUserRoles(form, user);

        userRepo.save(user);
        return "redirect:/user";
    }

    private static void toAddUserRoles(@RequestParam Map<String, String> form, @RequestParam("userId") User user) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
    }
}
