package com.myLearning.accountSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myLearning.accountSystem.dto.UserDTO;
import com.myLearning.accountSystem.service.UserService;

@Controller
public class AuthorizationController {

    private UserService userService;

    @Autowired
    AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        return "authorization/signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.signupNewUser(userDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", defaultValue = "false") boolean loginError, Model model) {
        if(loginError) {
            model.addAttribute("errorMessage", "Wrong login or password");
        }
        return "authorization/login";
    }

    @GetMapping("/loginSuccess")
    public String getLoginSuccessPage() {
        return "authorization/loginSuccess";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "authorization/logout";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "authorization/accessDenied";
    }
}
