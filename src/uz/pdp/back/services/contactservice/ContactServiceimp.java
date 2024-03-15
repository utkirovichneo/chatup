package uz.pdp.back.services.contactservice;

import uz.pdp.back.models.contact.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactServiceimp implements ContactService{

    private List<Contact> contactList;

    public ContactServiceimp() {
        this.contactList = new ArrayList<>();
    }

    @Override
    public void create(Contact contact) {
        if (contact == null) {
            return;
        }
        contactList.add(contact);
    }

    @Override
    public Contact edit(String id, String newContactName) {
        for (Contact contact : contactList) {
            if(contact.getUserID().equals(id)) {
                contact.setName(newContactName);
            return contact;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                contactList.remove(contact);
                return;
            }
        }
    }

    @Override
    public Contact get(String id) {
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getList() {
        return contactList;
    }

    @Override
    public List<Contact> getContactByID(String id) {
        List<Contact> contactListByid = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getUserID().equals(id)) {
                contactListByid.add(contact);
            }
        }
        return contactListByid;
    }

    @Override
    public List<String> contactsID(String id) {
        var contactsID = new ArrayList<String>();
        for (Contact contact : contactList) {
            if (Objects.equals(contact.getUserID(), id)) {
                contactsID.add(contact.getContactUserID());
            }
        }
        return contactsID;
    }
}
