package com.areebaahmad.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.areebaahmad.portfolio.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
