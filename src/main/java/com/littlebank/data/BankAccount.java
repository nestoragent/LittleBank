package com.littlebank.data;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
@Data
@Builder
@Entity
@Table(name = "bank_accounts")
public class BankAccount implements Serializable {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long account_id;
    @Column(nullable = false, updatable = false)
    private Long accountNumber;
    @Column(nullable = false)
    private String IBAN;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false)
    private Long bic;

    private BankAccount() {
    }

    public BankAccount(Long account_id, Long accountNumber, String IBAN, String bankName, Long bic) {
        this.accountNumber = accountNumber;
        this.IBAN = IBAN;
        this.bankName = bankName;
        this.bic = bic;
    }

    public BankAccount(Long accountNumber, String IBAN, String bankName, Long bic) {
        this.accountNumber = accountNumber;
        this.IBAN = IBAN;
        this.bankName = bankName;
        this.bic = bic;
    }

}
