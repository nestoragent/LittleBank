package com.littlebank.controller;

import com.littlebank.data.BankAccount;
import com.littlebank.util.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velichko-aa on 16.03.17.
 */
@Controller
@Slf4j
public class BaseController {

    private static final String VIEW_INDEX = "index.html";

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

//    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    @RequestMapping(value = "/create")
    public String create(@RequestParam String accountNumber, @RequestParam String IBAN,
                         @RequestParam String bankName, @RequestParam String bic) {
        final BankAccount account = BankAccount.builder()
                .accountNumber(Long.parseLong(accountNumber))
                .IBAN(IBAN)
                .bankName(bankName)
                .bic(Long.parseLong(bic))
                .build();
        System.out.println("res: " + account.toString());
        log.info("account: ", account.toString());
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public String update(@RequestParam String accountNumber, @RequestParam String IBAN,
            @RequestParam String bankName, @RequestParam String bic) {
        final BankAccount account = BankAccount.builder()
                .accountNumber(Long.parseLong(accountNumber))
                .IBAN(IBAN)
                .bankName(bankName)
                .bic(Long.parseLong(bic))
                .build();
        System.out.println("res: " + account.toString());
        log.info("account: ", account.toString());
        return VIEW_INDEX;
    }
}
