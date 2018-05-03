package com.littlebank.data;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
@Data
@Builder
@Entity
public class BankAccount {

    private @Id @GeneratedValue
    Long account_id;
    private Long accountNumber;
    private String IBAN;
    private String bankName;
    private Long bic;

    private BankAccount() {
    }

    public BankAccount(Long account_id, Long accountNumber, String IBAN, String bankName, Long bic) {
        this.account_id = account_id;
        this.accountNumber = accountNumber;
        this.IBAN = IBAN;
        this.bankName = bankName;
        this.bic = bic;
    }
}
