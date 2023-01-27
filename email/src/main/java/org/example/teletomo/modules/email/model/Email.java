package org.example.teletomo.modules.email.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private UUID id;
    private String sender;
    private List<String> recipients;
    private String subject;
    private String message; 
}