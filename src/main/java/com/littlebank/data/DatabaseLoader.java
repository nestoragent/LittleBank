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
        this.repository.save(new BankAccount(10L, 0L, "asasdasdd", "asd", 123L));
        this.repository.save(new BankAccount(12L, 2L, "as423423423d", "asd", 123213L));
    }
}
