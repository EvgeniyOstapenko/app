package com.example.app.controller;

import com.example.app.controller.utils.ControllerUtils;
import com.example.app.controller.utils.PaginationUtils;
import com.example.app.domain.Message;
import com.example.app.domain.User;
import com.example.app.repos.MessageRepo;
import com.example.app.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(required = false, defaultValue = "") String filter,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Message> page;

        if (filter != null && !filter.isEmpty()) {
            page = messageRepo.findByTag(filter, pageable);
        } else {
            page = messageRepo.findAll(pageable);
        }

        List pageBord = PaginationUtils.formPageBord(9, page.getNumber());


        List<Message> messages = page.getContent();

        model.addAttribute("messages", messages);

        model.addAttribute("pageBord", pageBord);
        model.addAttribute("users", user);


        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam("file") MultipartFile file) throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFile(message, file);

            messageRepo.save(message);
            Page<Message> page = messageRepo.findAll(pageable);
            model.addAttribute("page", page);
            List<Message> messages = page.getContent();

            model.addAttribute("messages", messages);

            model.addAttribute("url", "/main");
            List pageBord = PaginationUtils.formPageBord(9, page.getNumber());
            model.addAttribute("pageBord", pageBord);
            model.addAttribute("message", null);

        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user,
                               Model model,
                               @RequestParam(required = false) Message message,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Set<Message> messages = user.getMessages();
        Page<Message> page = messageRepo.findAll(pageable);

        List pageBord = PaginationUtils.formPageBord(9, page.getNumber());

        model.addAttribute("message", message);
        model.addAttribute("pageBord", pageBord);
        model.addAttribute("page", page);
        model.addAttribute("url", "/user-messages/" + user.getId().toString());
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("userChannel", user);
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("messages", messages);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{userId}")
    public String updateMessage(@AuthenticationPrincipal User currentUser,
                                @PathVariable Long userId,
                                @RequestParam("id") Long messageId,
                                Model model,
                                @RequestParam("text") String text,
                                @RequestParam("tag") String tag,
                                @RequestParam("file") MultipartFile file) throws IOException {


        if (messageId == null) return "redirect:/main";

        Message message = messageRepo.findById(messageId).orElseGet(null);
        model.addAttribute("message", message);

        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);
            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + userId;
    }

    private void saveFile(@Valid Message message,
                          @RequestParam("file") MultipartFile file) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }
}
