package com.jpmorgan.securityignite.xssSessionHijack.model;

import javax.persistence.*;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String message;

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
