package com.udemy.backendninjaprc.controller;

import com.udemy.backendninjaprc.constant.ViewConstant;
import com.udemy.backendninjaprc.model.ContactModel;
import com.udemy.backendninjaprc.service.ContactService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("/contactform")
    public ModelAndView redirectContactForm() {
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACT_FORM);
        mav.addObject("contactModel", new ContactModel());
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