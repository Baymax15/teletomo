package org.example.teletomo.modules.billing.dto;

import java.util.List;

import org.example.teletomo.modules.billing.model.Bill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillList {
    List<Bill> data;
}
