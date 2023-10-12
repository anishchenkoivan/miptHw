package hw1;

import java.util.ArrayList;

public class Client {
    final String name;
    long phoneNumber;
    final ArrayList<Account> accounts;

    public Client(String name, long phoneNumber, ArrayList<Account> accounts) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    public void createAccount(String currency, long amount) {
        accounts.add(new Account(currency, amount));
    }
}
