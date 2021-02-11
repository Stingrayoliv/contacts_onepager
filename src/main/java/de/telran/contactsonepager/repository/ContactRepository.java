package de.telran.contactsonepager.repository;

import de.telran.contactsonepager.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findByNameIgnoreCase(String name);

    List<Contact> findByLastNameIgnoreCase(String lastName);

    List<Contact> findByLastNameAndNameIgnoreCase(String lastName, String name);
}
