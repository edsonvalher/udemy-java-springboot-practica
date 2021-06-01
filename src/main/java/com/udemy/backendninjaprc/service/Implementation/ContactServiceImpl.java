package com.udemy.backendninjaprc.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import com.udemy.backendninjaprc.components.ContactConverter;
import com.udemy.backendninjaprc.entity.Contact;
import com.udemy.backendninjaprc.model.ContactModel;
import com.udemy.backendninjaprc.repository.ContactRepository;
import com.udemy.backendninjaprc.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository contactRepository;

    @Autowired
    @Qualifier("contactConverter")
    private ContactConverter contactConverter;

    @Override
    public ContactModel addContact(ContactModel contactModel) {
        Contact contact = new Contact();
        contact = contactRepository.save(contactConverter.ConvertModelToEntity(contactModel));
        return contactConverter.ConvertEntityToModel(contact);
    }

    @Override
    public List<ContactModel> listAllContacts() {

        List<Contact> contacts = contactRepository.findAll();
        List<ContactModel> contactModels = new ArrayList<ContactModel>();
        for (Contact contact : contacts) {
            contactModels.add(contactConverter.ConvertEntityToModel(contact));
        }
        return contactModels;
    }

    @Override
    public Contact findContactById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public void removeContact(int id) {
        Contact contact = new Contact();
        contact = findContactById(id);
        if (contact != null) {

            contactRepository.delete(contact);
        }

    }

}