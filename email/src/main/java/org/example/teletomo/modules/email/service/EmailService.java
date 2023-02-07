package org.example.teletomo.modules.email.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.teletomo.modules.email.dto.EmailList;
import org.example.teletomo.modules.email.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailService {
    List<Email> Emails = new ArrayList<>(loadEmails());

    @Autowired
    ObjectMapper mapper;

    public EmailList createEmail(Email request) {
        request.setId(UUID.randomUUID());
        Emails.add(request);
        return new EmailList(Emails);
    }

    public JsonNode deleteEmail(UUID id) {
        boolean status = Emails.removeIf(email -> email.getId().equals(id));
        return mapper.createObjectNode().put("data", status);
    }

    public Email getEmail(UUID id) {
        return Emails.stream().filter(email -> email.getId().equals(id))
                .findFirst().orElse(new Email());
    }

    public EmailList getEmails(JsonNode params) {
        Stream<Email> responseList = Emails.stream();
        if (params.has("subject")) {
            String subject = params.get("subject").asText().toLowerCase();
            responseList = responseList.filter(email -> email.getSubject().toLowerCase().contains(subject));
        }
        if (params.has("sender")) {
            String sender = params.get("sender").asText().toLowerCase();
            responseList = responseList.filter(email -> email.getSender().toLowerCase().contains(sender));
        }
        if (params.has("message")) {
            String message = params.get("message").asText().toLowerCase();
            responseList = responseList.filter(email -> email.getSender().toLowerCase().contains(message));
        }
        return new EmailList(responseList.collect(Collectors.toList()));
    }

    private static List<Email> loadEmails() {
        List<String> users = Arrays.asList("moinefou@example.com", "lcheng@example.com", "michiel@example.com",
                "sscorpio@example.com", "mavilar@example.com", "forsberg@example.com", "howler@example.com",
                "mcsporran@example.com", "jadavis@example.com", "garland@example.com", "raides@example.com",
                "jfieka@example.com");

        List<Email> initialMails = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < users.size(); i++) {
            List<String> temp = new ArrayList<>(users);
            Collections.shuffle(temp);

            List<String> recipients = temp.subList(1, (random.nextInt(users.size() - 1) + 2));

            initialMails.add(new Email(UUID.randomUUID(),
                    temp.get(0),
                    recipients,
                    OffsetDateTime.now(),
                    "Test email subjectline" + i,
                    "Hello from " + temp.get(0)));
        }

        return initialMails;
    }
}
