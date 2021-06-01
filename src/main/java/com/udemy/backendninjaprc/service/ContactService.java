package com.udemy.backendninjaprc.service;

import java.util.List;

import com.udemy.backendninjaprc.entity.Contact;
import com.udemy.backendninjaprc.model.ContactModel;

public interface ContactService {

    public abstract ContactModel addContact(ContactModel contactModel);

    public abstract List<ContactModel> listAllContacts();

    public abstract Contact findContactById(int id);

    public abstract void removeContact(int id);

}