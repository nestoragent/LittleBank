package com.littlebank.logic;

import lombok.Data;
import lombok.ToString;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
@Data
@ToString
public class BankAccount {

    private Long account_id;
    private Long accountNumber;
    private String IBAN;
    private String bankName;
    private Long bic;
}
