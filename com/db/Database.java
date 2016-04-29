package com.db;

import com.account.*;
import com.client.*;

import java.util.HashMap;

public class Database {
    private HashMap<Integer, Client> clientlist;
    private HashMap<Integer, Account> accountlist;

    public Database() {
        accountlist = new HashMap<Integer, Account>();
        clientlist = new HashMap<Integer, Client>();
    }

    public boolean putAccount(Account account) {
        if (accountlist.containsKey(account.getAccountID())) {
            return false;
        }
        accountlist.put(account.getAccountID(), account);
        return true;
    }

    public Account getAccount(int id) {
        return accountlist.get(id);
    }

    public boolean deleteAccount(int accountID) {
        if (accountlist.containsKey(accountID)) {
            accountlist.remove(accountID);
            return true;
        }
        return false;
    }

    public boolean updateAccount(Account account) {
        if (accountlist.containsKey(account.getAccountID())) {
            accountlist.put(account.getAccountID(), account);
            return true;
        }
        return false;
    }

    public boolean putClient(Client client) {
        if (clientlist.containsKey(client.getClientID())) {
            return false;
        }
        clientlist.put(client.getClientID(), client);
        return true;
    }

    public boolean deleteClient(int id) {
        if (clientlist.containsKey(id)) {
            clientlist.remove(id);
            return true;
        }
        return false;
    }

    public boolean updateClient(Client client) {
        if (clientlist.containsKey(client.getClientID())) {
            clientlist.put(client.getClientID(), client);
            return true;
        }
        return false;
    }

    public Client getClient(int clientID) {
        if (clientlist.containsKey(clientID)) {
            return clientlist.get(clientID);
        }
        return null;
    }
}
