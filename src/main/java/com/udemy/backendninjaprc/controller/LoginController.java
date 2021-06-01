package com.udemy.backendninjaprc.controller;

import com.udemy.backendninjaprc.constant.ViewConstant;
import com.udemy.backendninjaprc.model.UserCredential;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/")
    public RedirectView redirectToLogin() {
        LOG.info("METHOD: redirectToLogin()");
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {

        LOG.info("METHOD: showLoginForm() -- PARAMS: error=" + error + " logout=" + logout);
        ModelAndView mav = new ModelAndView(ViewConstant.LOGIN);
        mav.addObject("error", error);
        mav.addObject("logout", logout);
        mav.addObject("userCredentials", new UserCredential());
        LOG.info("Returning to login view");

        return mav;
    }

    @PostMapping("/logincheck")
    public ModelAndView LoginCheck(@ModelAttribute(name = "userCredentials") UserCredential userCredential) {

        LOG.info("METHOD: logincheck() -- PARAMS: " + userCredential.toString());
        ModelAndView mav = new ModelAndView("contacts");
        if (userCredential.getUsername().equals(("user")) && userCredential.getPassword().equals(("user"))) {
            LOG.info("Returning to contacts view");
            mav = new ModelAndView(ViewConstant.CONTACTS_REDIRECT);
        } else {

            mav = new ModelAndView("redirect:login?error");
            LOG.info("redirect to login error");
        }

        return mav;
    }

}