package com.littlebank.logic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
@Data
@AllArgsConstructor
@Builder
public class BankAccount {

    private Long account_id;
    private Long accountNumber;
    private String IBAN;
    private String bankName;
    private Long bic;
}
