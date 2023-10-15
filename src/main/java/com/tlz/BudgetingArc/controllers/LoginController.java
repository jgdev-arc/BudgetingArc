package com.tlz.BudgetingArc.controllers;

import com.tlz.BudgetingArc.domain.User;
import com.tlz.BudgetingArc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(ModelMap model) {
        User user = new User();
        model.put("user", user);

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(ModelMap model) {
        User user = new User();
        model.put("user", user);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute User user, ModelMap model) {
        try {
            if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getConfirmPassword())) {
                throw new IllegalArgumentException("Must enter a password");
            }

            if (!user.getPassword().equals(user.getConfirmPassword())) {
                throw new IllegalArgumentException("Password mismatch");
            }

            user = userService.saveUser(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/budget";
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            model.put("error", e.getMessage());
            return "register";
        }
    }

}
