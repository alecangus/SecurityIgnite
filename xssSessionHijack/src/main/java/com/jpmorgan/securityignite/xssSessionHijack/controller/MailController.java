package com.jpmorgan.securityignite.xssSessionHijack.controller;

import com.jpmorgan.securityignite.xssSessionHijack.model.Mail;
import com.jpmorgan.securityignite.xssSessionHijack.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MailController {
    public static final String GUEST_WELCOME = "Welcome to the XSS Priviledge Escalation Game! Only the admin can proceed to the next level. Why not drop him a message and plead with him to make you an admin too?";
    @Autowired
    private MailRepository mailRepository;

    @GetMapping("/")
    public String homepage(Model model, @CookieValue(value = "session-ida", defaultValue = "") String guid) {
        model.addAttribute("message", GUEST_WELCOME);
        // Get cookie
        if (!guid.isEmpty() && guid.equals("b8afc16b-1b1e-4a79-825b-360f141fa0f8")) {
            model.addAttribute("message", "Hi admin! Here's the flag [BewareTheCookieMonster]");
        }
        return "index";
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String submitMessage(@ModelAttribute Mail message,
                                Model model,
                                @CookieValue(value = "session-id", defaultValue = "") String guid) {
        mailRepository.save(message);
        model.addAttribute("message", GUEST_WELCOME);
        // Get cookie
        if (!guid.isEmpty() && guid.equals("b8afc16b-1b1e-4a79-825b-360f141fa0f8")) {
            model.addAttribute("message", "Hi admin! Here's the flag [BewareTheCookieMonster]");
        }
        return "index";
    }

    @GetMapping("/preview")
    @ResponseStatus(HttpStatus.OK)
    public String previewMessage(Model model, @RequestParam("message") String message) {
        model.addAttribute("message", message);
        return "preview";
    }

    @GetMapping("/mail/{id}")
    // document.cookie="guid=b8afc16b-1b1e-4a79-825b-360f141fa0f8"
    public String getMailById(Model model,
            @PathVariable long id,
                              @CookieValue(value = "session-id", defaultValue = "") String guid) {
        // Get cookie
        if (guid.isEmpty() || !guid.equals("b8afc16b-1b1e-4a79-825b-360f141fa0f8")) {
            return "notAuthorised";
        }

        Mail mail = mailRepository.findById(id);
        model.addAttribute("message", mail.getMessage());
        return "getMail";
    }

    @GetMapping("/getAllMail")
    // document.cookie="session-id=b8afc16b-1b1e-4a79-825b-360f141fa0f8"
    public String getAllMail(Model model,
                              @CookieValue(value = "session-id", defaultValue = "") String guid) {
        // Get cookie
        if (guid.isEmpty() || !guid.equals("b8afc16b-1b1e-4a79-825b-360f141fa0f8")) {
            return "notAuthorised";
        }

        List<Mail> mails = mailRepository.findAll();
        System.out.println(mails.size());
        model.addAttribute("mails", mails);
        return "getAllMail";
    }

    @GetMapping("/numberOfMessages")
    @ResponseBody
    public Long getNumberOfMessages() {
        return mailRepository.count();
    }

}
