package com.jpmorgan.securityignite.xssSessionHijack.repository;

import com.jpmorgan.securityignite.xssSessionHijack.model.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {
    Mail findById(long id);
    List<Mail> findAll();
}
