package com.udemy.backendninjaprc.components;

import com.udemy.backendninjaprc.entity.Contact;
import com.udemy.backendninjaprc.model.ContactModel;

import org.springframework.stereotype.Component;

@Component("contactConverter")
public class ContactConverter {

    public Contact ConvertModelToEntity(ContactModel contactModel) {
        Contact contact = new Contact();
        contact.setId(contactModel.getId());
        contact.setCity(contactModel.getCity());
        contact.setFirstname(contactModel.getFirstname());
        contact.setLastname(contactModel.getLastname());
        contact.setTelephone(contactModel.getTelephone());
        return contact;

    }

    public ContactModel ConvertEntityToModel(Contact contact) {
        ContactModel contactModel = new ContactModel();
        contactModel.setId(contact.getId());
        contactModel.setCity(contact.getCity());
        contactModel.setFirstname(contact.getFirstname());
        contactModel.setLastname(contact.getLastname());
        contactModel.setTelephone(contact.getTelephone());
        return contactModel;
    }
}