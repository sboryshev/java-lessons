package com.issart.boryshev.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ForwardingSet;

public class Contacts extends ForwardingSet<ContactData> {

    private final Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<>(contacts);
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

    public ContactData getById(int id) {
        ContactData contactData = null;
        for (ContactData c : this) {
            if (c.getId() == id) {
                contactData = c;
            }
        }
        return contactData;
    }
}
