package com.littlebank.controller;

import com.littlebank.data.BankAccount;
import com.littlebank.data.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by velichko-aa on 16.03.17.
 */
@Controller
@Slf4j
public class BaseController {

    private static final String VIEW_INDEX = "index.html";
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @RequestMapping(value = "/")
    public String welcome() {
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam String accountNumber) {
        System.out.println("reqs: " + accountNumber);
        log.info("req: ", accountNumber);
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/create")
    public String create(@RequestParam String accountNumber, @RequestParam String IBAN,
                         @RequestParam String bankName, @RequestParam String bic) {
        final BankAccount account = BankAccount.builder()
                .accountNumber(Long.parseLong(accountNumber))
                .IBAN(IBAN)
                .bankName(bankName)
                .bic(Long.parseLong(bic))
                .build();
        this.bankAccountRepository.save(account);
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/update")
    public String update(@RequestParam String accountNumber, @RequestParam String IBAN,
                         @RequestParam String bankName, @RequestParam String bic) {

        BankAccount account = bankAccountRepository.findAll().stream().filter(
                el -> el.getAccountNumber().equals(Long.parseLong(accountNumber))).findFirst().orElse(null);
        if (null == account) {
            account = BankAccount.builder()
                    .accountNumber(Long.parseLong(accountNumber))
                    .IBAN(IBAN)
                    .bankName(bankName)
                    .bic(Long.parseLong(bic))
                    .build();
        } else {
            account.setIBAN(IBAN);
            account.setBankName(bankName);
            account.setBic(Long.parseLong(bic));
        }

        System.out.println("res: " + account.toString());
        log.info("account: ", account.toString());
        this.bankAccountRepository.save(account);
        return VIEW_INDEX;
    }
}
