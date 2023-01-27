package org.example.teletomo.modules.email.dto;

import java.util.List;

import org.example.teletomo.modules.email.model.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailList {
    List<Email> emails;
}
