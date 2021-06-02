package com.udemy.backendninjaprc.controller;

import com.udemy.backendninjaprc.constant.ViewConstant;
import com.udemy.backendninjaprc.model.ContactModel;
import com.udemy.backendninjaprc.service.ContactService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @GetMapping("/cancel")
    public ModelAndView redirectCancel() {
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_REDIRECT);
        return mav;
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')") //* se puede restringir el acceso por
    // role como este no existe no permite y da un error 403
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") //* esta
    // anotacion es permitida

    @PreAuthorize("permitAll()") // * y tambien se puede colcoar a nivel de clase o metodo de servicio
    @GetMapping("/contactform")
    public ModelAndView redirectContactForm(@RequestParam(name = "id", required = false) int id) {
        LOG.info("METHOD: redirectContactForm() -- PARAMS: " + id);
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACT_FORM);
        ContactModel contactModel = new ContactModel();
        if (id != 0) {
            contactModel = contactService.findContactModelById(id);
            mav.addObject("contactModel", contactModel);
        } else {
            mav.addObject("contactModel", contactModel);

        }

        return mav;
    }

    @PostMapping("/addcontact")
    public ModelAndView addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel) {
        LOG.info("METHOD: addContact() -- PARAMS: " + contactModel.toString());
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_REDIRECT);

        if (contactService.addContact(contactModel) != null) {
            mav.addObject("result", 1);
        } else {
            mav.addObject("result", 0);
        }
        return mav;

    }

    @GetMapping("/showcontacts")
    public ModelAndView showContacts() {
        LOG.info("METHOD: showContacts() ");
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        mav.addObject("username", user.getUsername());

        mav.addObject("contacts", contactService.listAllContacts());
        return mav;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
        LOG.info("METHOD: removeContact() ");
        contactService.removeContact(id);
        return showContacts();
    }

}