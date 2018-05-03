package com.littlebank.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final BankAccountRepository repository;

    @Autowired
    public DatabaseLoader(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
//        BankAccount bankAccount1 = new BankAccount(10L, 0L, "asasdasdd", "asd", 123L);
//        BankAccount bankAccount2 = new BankAccount(12L, 2L, "as423423423d", "asd", 123213L);
//        final List<BankAccount> list = new ArrayList<>();
//        list.add(bankAccount1);
//        list.add(bankAccount2);
//        model.addAttribute("accounts", JsonHelper.convertAnObjectListToJsonArray(list));
        this.repository.save(new BankAccount(10L, 0L, "asasdasdd", "asd", 123L));
        this.repository.save(new BankAccount(12L, 2L, "as423423423d", "asd", 123213L));
    }
}
