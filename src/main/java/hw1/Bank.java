package hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Long, Client> clients;

    public Bank() {
        this.clients = new HashMap<>();
    }

    public Client getClient(long phoneNumber) {
        return clients.get(phoneNumber);
    }

    public void createClient(String name, long phoneNumber, ArrayList<Account> accounts) {
        Client client = new Client(name, phoneNumber, accounts);
        clients.put(client.phoneNumber, client);
    }

    public void removeClient(Client client) {
        clients.remove(client.phoneNumber);
    }

    public Map<String, Long> getSummaryBalance() {
        Map<String, Long> summaryBalance = new HashMap<>();
        for (Client client: clients.values()) {
            for (Account account: client.accounts) {
                summaryBalance.putIfAbsent(account.currency, 0L);
                summaryBalance.put(account.currency, summaryBalance.get(account.currency) + account.amount);
            }
        }

        return summaryBalance;
    }
}
