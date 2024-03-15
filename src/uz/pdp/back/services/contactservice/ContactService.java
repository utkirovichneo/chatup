package uz.pdp.back.services.contactservice;

import uz.pdp.back.models.contact.Contact;
import uz.pdp.back.services.baseservice.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact> {
    List<Contact> getContactByID(String id);
    List<String> contactsID(String id);
}