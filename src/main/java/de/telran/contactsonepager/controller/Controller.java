package de.telran.contactsonepager.controller;

import de.telran.contactsonepager.dto.ContactDto;
import de.telran.contactsonepager.model.Contact;
import de.telran.contactsonepager.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/contacts")
public class Controller {
    final ContactService contactService;

    public Controller(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ContactDto create(@RequestBody ContactDto contactDto) {
        Contact contact = contactService.create(contactDto.name, contactDto.lastName,
                contactDto.age);
        contactDto.id = contact.getId();
        return contactDto;
    }

    @GetMapping("{id}")
    public ContactDto get(@PathVariable int id) {
        Contact contact = contactService.get(id);

        ContactDto contactDto = new ContactDto(
                contact.getId(),
                contact.getName(),
                contact.getLastName(),
                contact.getAge()
        );
        return contactDto;
    }

    @GetMapping
    public List<ContactDto> getAll() {
        List<Contact> contacts = contactService.getAll();

        return contacts.stream().
                map(contact -> new ContactDto(
                        contact.getId(),
                        contact.getName(),
                        contact.getLastName(),
                        contact.getAge()
                ))
                .collect(Collectors.toList());
    }

    @PutMapping
    //not necessary
    @ResponseStatus(HttpStatus.NO_CONTENT)//204 status
    public void edit(@RequestBody ContactDto contactDto) {
        contactService.edit(
                contactDto.id,
                contactDto.name,
                contactDto.lastName,
                contactDto.age
        );
    }

    @DeleteMapping("{id}")
    public ContactDto remove(@PathVariable int id) {
        Contact contact = contactService.get(id);
        return new ContactDto(
                contact.getId(),
                contact.getName(),
                contact.getLastName(),
                contact.getAge()
        );
    }

}
