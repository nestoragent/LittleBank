package com.littlebank.controller;

import com.littlebank.logic.BankAccount;
import com.littlebank.util.HibernateUtil;
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

    private static final String VIEW_INDEX = "index";

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String welcome(ModelMap model) {
        BankAccount bankAccount1 = new BankAccount(10L, 0L, "asasdasdd", "asd", 123L);
        BankAccount bankAccount2 = new BankAccount(12L, 2L, "as423423423d", "asd", 123213L);
        final List<BankAccount> list = new ArrayList<>();
        list.add(bankAccount1);
        list.add(bankAccount2);
        model.addAttribute("accounts", JsonHelper.convertAnObjectListToJsonArray(list));
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    public String delete(@RequestParam String account_id) {
        log.info("req: ", account_id);
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public String update(@RequestParam String account_id, @RequestParam String accountNumber, @RequestParam String IBAN,
            @RequestParam String bankName, @RequestParam String bic) {
        final BankAccount account = BankAccount.builder()
                .account_id(Long.parseLong(account_id))
                .accountNumber(Long.parseLong(accountNumber))
                .IBAN(IBAN)
                .bankName(bankName)
                .bic(Long.parseLong(bic))
                .build();
        log.info("account_id: ", account_id);
        log.info("accountNumber: ", accountNumber);
        log.info("IBAN: ", IBAN);
        log.info("bankName: ", bankName);
        log.info("bic: ", bic);
        log.info("account: ", account);
        return VIEW_INDEX;
    }
}
