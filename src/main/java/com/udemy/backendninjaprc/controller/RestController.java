package com.udemy.backendninjaprc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import com.udemy.backendninjaprc.model.ContactModel;
import com.udemy.backendninjaprc.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @GetMapping("/checkrest")
    public ResponseEntity<List<ContactModel>> checkRest() {

        // return new ResponseEntity<String>("ok", HttpStatus.OK);

        // ContactModel cm = new ContactModel(2, "Mikel", "Perez", "12123133",
        // "Madrid");
        // return new ResponseEntity<ContactModel>(cm, HttpStatus.OK);
        List<ContactModel> lista = new ArrayList<ContactModel>();
        lista = contactService.listAllContacts();
        return new ResponseEntity<List<ContactModel>>(lista, HttpStatus.OK);

    }

}