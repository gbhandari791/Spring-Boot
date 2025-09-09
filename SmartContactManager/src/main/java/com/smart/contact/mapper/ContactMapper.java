package com.smart.contact.mapper;

import org.springframework.stereotype.Component;

import com.smart.contact.dto.ContactDTO;
import com.smart.contact.entities.Contact;

@Component
public class ContactMapper {

	public ContactDTO toContactDto(Contact contact) {
		if(contact == null) return null;
		
		ContactDTO dto = new ContactDTO();
		dto.setId(contact.getId());
		dto.setName(contact.getName());
		dto.setNickName(contact.getNickName());
		dto.setWork(contact.getWork());
		dto.setEmail(contact.getEmail());
		dto.setPhone(contact.getPhone());
		dto.setImage(contact.getImage());
		dto.setDescription(contact.getDescription());
		dto.setUser(contact.getUser());
		return dto;
	}
	
	public Contact toContactDto(ContactDTO dto) {
		if(dto == null) return null;
		
		Contact contact = new Contact();
		contact.setId(dto.getId());
		contact.setName(dto.getName());
		contact.setNickName(dto.getNickName());
		contact.setWork(dto.getWork());
		contact.setEmail(dto.getEmail());
		contact.setPhone(dto.getPhone());
		contact.setImage(dto.getImage());
		contact.setDescription(dto.getDescription());
		contact.setUser(dto.getUser());
		return contact;
	}
}
