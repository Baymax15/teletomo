package org.example.teletomo.modules.billing.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    Integer price;
    String paymentMode;
    UUID reference;
}
